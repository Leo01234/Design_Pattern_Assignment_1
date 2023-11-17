package hly.game;

import hly.game.Items.ball.Ball;
import hly.game.Items.Board;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    private final JSONObject jsonObject;
    private static final double GRAVITY = 10.0 / 60;
    private final Ball ball;
    private final Board board;
    private final Canvas canvas;
    private final double[] canvasDim = {0.0, 0.0};

    public Game(String path,double canvasDimX, double canvasDimY, Canvas canvas) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));

        // convert Object to JSONObject
        jsonObject = (JSONObject) object;

        canvasDim[0] = canvasDimX;
        canvasDim[1] = canvasDimY;
        ball = new Ball(100.0, 100.0);
        reset();
        board = new Board(canvasDimX, canvasDimY);
        this.canvas = canvas;
    }

    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        ball.addToGroup(groupChildren);
        board.addToGroup(groupChildren);
        board.registerMouseAction();
    }

    // tick() is called every frame, handle main game logic here
    public void tick() {
        ball.setYVel(ball.getYVel() + GRAVITY);
        handleCollision();
        ball.move();
    }

    private void handleCollision() {
        Bounds canvasBounds = canvas.getBoundsInLocal();
        Bounds ballBounds = ball.getNode().getBoundsInLocal();
        Bounds boardBounds = board.getNode().getBoundsInLocal();
        if (ballBounds.intersects(boardBounds)) {
            ball.setYVel(-ball.getYVel());
        }
        if (ballBounds.getMinX() <= canvasBounds.getMinX() ||
                ballBounds.getMaxX() >= canvasBounds.getMaxX()) {
            ball.setXVel(-ball.getXVel());
        }
        if (ballBounds.getMaxY() >= canvasBounds.getMaxY()) {
            reset();
        }
    }

    public void reset() {
        ball.setXPos(100);
        ball.setYPos(100);
        ball.setXVel(50.0 / 60);
        ball.setYVel(0);
    }
}

