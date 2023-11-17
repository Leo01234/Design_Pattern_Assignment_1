package hly.game.Items.ball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Leo01234
 * @version 1.0
 */
public class BlueBall extends Ball {

    @Override
    public void setColour() {
        ((Shape) getNode()).setFill(Color.valueOf("blue"));
    }

}
