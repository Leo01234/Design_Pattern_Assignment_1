package hly.game;

import hly.game.Items.Table;
import hly.game.Items.ball.Ball;
import hly.game.factory.BallFactory;
import hly.game.factory.TableFactory;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private final JSONObject jsonObject;
    private final Canvas canvas;
    private final Table table;
    private final ArrayList<Ball> balls;

    public Game(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));

        // convert Object to JSONObject
        jsonObject = (JSONObject) object;

        // use factory to create table
        table = (Table) new TableFactory().createDrawable(jsonObject);

        //create canvas according to table size
        canvas = new Canvas(table.getXSize(), table.getYSize());

        //use factory to create balls
        balls = new ArrayList<>();
        BallFactory ballFactory = new BallFactory();
        Ball ball;
        while ((ball = (Ball) ballFactory.createDrawable(jsonObject)) != null) {
            balls.add(ball);
        }
    }

    public void setStage(Stage stage) {
        stage.setWidth(table.getXSize());
        stage.setHeight(table.getYSize());
        stage.setResizable(false);
    }

    public void addCanvas(Group root) {
        root.getChildren().add(canvas);
    }

    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        for (Ball ball : balls) {
            ball.addToGroup(groupChildren);
        }
        //board.registerMouseAction();
    }

    // tick() is called every frame, handle main game logic here
    public void tick() {
        //friction for all balls
        double friction = table.getFriction();
        for (Ball ball : balls) {
            Point2D vel = ball.getVel();
            if (vel != Point2D.ZERO) {
                double newMagnitude = vel.magnitude() - friction;
                if (newMagnitude <= 0) {
                    ball.setVel(Point2D.ZERO);
                } else {
                    ball.setVel(vel.normalize().multiply(newMagnitude));
                }
            }
        }
        handleCollision();
        //all balls move
        for (Ball ball : balls) {
            ball.move();
        }
    }

    private void handleCollision() {
        Bounds tableBounds = table.getNode().getBoundsInLocal();
        for (Ball ballA : balls) {
            //only moving balls can collide with other balls / table edges
            if (ballA.getVel() != Point2D.ZERO) {
                //with other balls
                for (Ball ballB : balls) {
                    if (ballA.getPos().subtract(ballB.getPos()).magnitude()
                            <= 2 * App.BALL_RADIUS) {
                        Pair<Point2D, Point2D> velPair = Collision.calculateCollision(
                                ballA.getPos(), ballA.getVel(), ballA.getMass(),
                                ballB.getPos(), ballB.getVel(), ballB.getMass());
                        ballA.setVel(velPair.getKey());
                        ballB.setVel(velPair.getValue());
                    }
                }

                //with table edges
                Bounds ballBounds = ballA.getNode().getBoundsInLocal();
                if (ballBounds.getMinX() <= tableBounds.getMinX() ||
                        ballBounds.getMaxX() >= tableBounds.getMaxX()) {
                    Point2D vel = ballA.getVel();
                    double xVel = vel.getX();
                    double yVel = vel.getY();
                    ballA.setVel(new Point2D(-xVel,yVel));
                }

                if (ballBounds.getMinY() <= tableBounds.getMinY() ||
                        ballBounds.getMaxY() >= tableBounds.getMaxY()) {
                    Point2D vel = ballA.getVel();
                    double xVel = vel.getX();
                    double yVel = vel.getY();
                    ballA.setVel(new Point2D(xVel,-yVel));
                }
            }
        }
    }
}

