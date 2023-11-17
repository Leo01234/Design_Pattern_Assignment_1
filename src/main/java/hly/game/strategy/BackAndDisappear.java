package hly.game.strategy;

import hly.game.Items.ball.Ball;

/**
 * @author Leo01234
 * @version 1.0
 */
public class BackAndDisappear implements BallInHoleStrategy{
    boolean hasAlreadyFallOnce = false;
    @Override
    public void execute(Ball ball) {
        if(hasAlreadyFallOnce){
            ball.removeFromGroup();
        }else {
            ball.resetPos();
            hasAlreadyFallOnce=true;
        }
    }
}
