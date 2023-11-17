package hly.game.ballbuilder;

import hly.game.Items.ball.Ball;
import hly.game.strategy.BallInHoleStrategy;

/**
 * @author Leo01234
 * @version 1.0
 */
public interface BallBuilder {

    void createBall(String colour);

    void setInitPosition(double xPos, double yPos);
    void setPosition(double xPos, double yPos);

    void setRadius();
    void setColour();

    void setVel(double xVel, double yVel);

    void setMass(double mass);

    void setStrategy(String colour);

    Ball getResult();
}
