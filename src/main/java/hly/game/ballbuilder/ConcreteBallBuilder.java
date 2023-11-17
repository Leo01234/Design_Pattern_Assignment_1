package hly.game.ballbuilder;

import hly.game.App;
import hly.game.Items.ball.Ball;
import hly.game.Items.ball.BlueBall;
import hly.game.Items.ball.RedBall;
import hly.game.Items.ball.WhiteBall;
import javafx.geometry.Point2D;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ConcreteBallBuilder implements BallBuilder {
    Ball ball;

    @Override
    public void createBall(String colour) {
        switch (colour) {
            case "white":
                ball = new WhiteBall();
                break;
            case "red":
                ball = new RedBall();
                break;
            case "blue":
                ball = new BlueBall();
                break;
            default:
                throw new RuntimeException("Colour config wrong!");
        }
    }

    @Override
    public void setPosition(double xPos, double yPos) {
        ball.setPos(new Point2D(xPos, yPos));
    }

    @Override
    public void setRadius() {
        ball.setRadius(App.BALL_RADIUS);
    }

    @Override
    public void setColour() {
        ball.setColour();
    }

    @Override
    public void setVel(double xVel, double yVel) {
        ball.setVel(new Point2D(xVel, yVel));
    }

    @Override
    public void setMass(double mass) {
        ball.setMass(mass);
    }

    @Override
    public Ball getResult() {
        return ball;
    }
}
