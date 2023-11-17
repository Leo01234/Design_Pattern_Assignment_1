package hly.game.strategy;

import hly.game.Items.ball.Ball;

/**
 * @author Leo01234
 * @version 1.0
 */
public class Disappear implements BallInHoleStrategy{
    @Override
    public void execute(Ball ball) {
        ball.removeFromGroup();
    }
}
