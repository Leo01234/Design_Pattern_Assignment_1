package hly.game.ballbuilder;

import hly.game.Items.ball.Ball;
import hly.game.Items.ball.BlueBall;
import hly.game.Items.ball.RedBall;
import hly.game.Items.ball.WhiteBall;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ConcreteBallBuilder implements BallBuilder{
    Ball ball;
    @Override
    public void createBall(String colour) {
       switch (colour){
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
               throw new RuntimeException("Colour config wrong!")
       }
    }

    @Override
    public void setPosition(double xPos, double yPos) {
        ball.setXPos(xPos);
        ball.setYPos(yPos);
    }

    @Override
    public void setRadius() {
        ball.setRadius(RADIUS);
    }

    @Override
    public void setVel(double xVel, double yVel) {
        ball.setXVel(xVel);
        ball.setYVel(yVel);
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
