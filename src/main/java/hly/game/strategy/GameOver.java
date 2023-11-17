package hly.game.strategy;

import hly.game.Items.ball.Ball;

/**
 * @author Leo01234
 * @version 1.0
 */
public class GameOver implements BallInHoleStrategy{
    @Override
    public void execute(Ball ball) {
        System.exit(0);
    }
}
