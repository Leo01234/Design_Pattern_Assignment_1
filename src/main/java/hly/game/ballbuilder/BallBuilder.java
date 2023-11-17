package hly.game.ballbuilder;

import hly.game.Items.ball.Ball;

/**
 * @author Leo01234
 * @version 1.0
 */
public interface BallBuilder {

    void createBall(String colour);

    void setPosition(double xPos, double yPos);

    void setRadius();

    void setVel(double xVel, double yVel);

    void setMass(double mass);

    Ball getResult();
}
