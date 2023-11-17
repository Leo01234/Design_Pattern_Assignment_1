package hly.game;

import hly.game.Items.Hole;
import hly.game.Items.Table;
import hly.game.Items.ball.Ball;
import hly.game.Items.ball.WhiteBall;
import hly.game.factory.BallFactory;
import hly.game.factory.TableFactory;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private final JSONObject jsonObject;
    private final Canvas canvas;
    private final Table table;
    //keep track of balls that still exist(i.e. is in group)
    private final ArrayList<Ball> balls;
    private final Hole[] holes;

    private final Text victoryText;
    private boolean isVictory = false;

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

        //create holes
        holes = new Hole[6];
        holes[0] = new Hole(App.HOLE_EDGE_DISTANCE, App.HOLE_EDGE_DISTANCE);
        holes[1] = new Hole(App.HOLE_EDGE_DISTANCE, getHeight() - App.HOLE_EDGE_DISTANCE);
        holes[2] = new Hole(getWidth() - App.HOLE_EDGE_DISTANCE, App.HOLE_EDGE_DISTANCE);
        holes[3] = new Hole(getWidth() - App.HOLE_EDGE_DISTANCE, getHeight() - App.HOLE_EDGE_DISTANCE);
        holes[4] = new Hole(getWidth() / 2, App.HOLE_EDGE_DISTANCE);
        holes[5] = new Hole(getWidth() / 2, getHeight() - App.HOLE_EDGE_DISTANCE);

        //create empty text for further use
        victoryText = new Text();

    }

    public double getWidth() {
        return table.getXSize();
    }

    public double getHeight() {
        return table.getYSize();
    }

    public void addCanvas(Group root) {
        root.getChildren().add(canvas);
    }

    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();

        //add table, table is on the lowest layer
        table.setGroup(groupChildren);
        table.addToGroup();
        //add holes, holes is under the balls
        for (Hole hole : holes) {
            hole.setGroup(groupChildren);
            hole.addToGroup();
        }
        //finally the balls
        for (Ball ball : balls) {
            ball.setGroup(groupChildren);
            ball.addToGroup();
            if (ball instanceof WhiteBall whiteBall) {
                whiteBall.initIndicationLine();
                whiteBall.registerMouseAction();
            }
        }
        //and also the text
        groupChildren.add(victoryText);
    }

    // tick() is called every frame, handle main game logic here
    public void tick() {
        removeNotExistBalls();
        ballInHole();
        checkVictory();
        frictionCalculation();
        handleCollision();
        move();
    }


    //remove balls that not exist(i.e. not in group)
    private void removeNotExistBalls() {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            if (!ball.getIsInGroup()) {
                iterator.remove();
            }
        }
    }

    //detect ball in hole
    private void ballInHole() {
        for (Ball ball : balls) {
            for (Hole hole : holes) {
                if (ball.getPos().subtract(hole.getPos()).magnitude()
                        <= App.HOLE_RADIUS) {
                    ball.ballInHole();
                }
            }
        }
    }

    private void checkVictory(){
        if(!isVictory){
            for(Ball ball:balls){
                if(!(ball instanceof WhiteBall)){
                    return;
                }
            }
            isVictory=true;
            victoryText.setBoundsType(TextBoundsType.VISUAL);
            victoryText.setFont(new Font(20));
            victoryText.setText("你赢了！");
            victoryText.setTextAlignment(TextAlignment.CENTER);
            victoryText.setTextOrigin(VPos.CENTER);

            victoryText.setX(getWidth() / 2 - victoryText.getLayoutBounds().getWidth() / 2);
            victoryText.setY(getHeight() / 2);
        }
    }
    //friction for all balls
    private void frictionCalculation() {
        double friction = table.getFriction();
        for (Ball ball : balls) {
            Point2D vel = ball.getVel();
            if (vel != Point2D.ZERO) {
                double newMagnitude = vel.magnitude() - friction * ball.getMass() / App.FRICTION_ADJUST;
                if (newMagnitude <= 0) {
                    ball.setVel(Point2D.ZERO);
                } else {
                    ball.setVel(vel.normalize().multiply(newMagnitude));
                }
            }
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
                if ((ballBounds.getMinX() <= tableBounds.getMinX() && ballA.getVel().getX() < 0)
                        || (ballBounds.getMaxX() >= tableBounds.getMaxX() && ballA.getVel().getX() > 0)) {
                    ballA.reverseXVel();
                }
                if ((ballBounds.getMinY() <= tableBounds.getMinY() && ballA.getVel().getY() < 0)
                        || (ballBounds.getMaxY() >= tableBounds.getMaxY() && ballA.getVel().getY() > 0)) {
                    ballA.reverseYVel();
                }

            }
        }
    }

    //all balls move
    private void move() {
        for (Ball ball : balls) {
            ball.move();
        }
    }
}

