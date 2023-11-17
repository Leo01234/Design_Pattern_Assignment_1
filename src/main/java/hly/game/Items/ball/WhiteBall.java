package hly.game.Items.ball;

import hly.game.App;
import hly.game.Items.IndicationLine;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Leo01234
 * @version 1.0
 */
public class WhiteBall extends Ball{
    private IndicationLine iL;
    public void initIndicationLine(){
        iL = new IndicationLine();
        iL.setGroup(getGroup());
    }
    @Override
    public void setColour() {
        ((Shape) getNode()).setFill(Color.valueOf("white"));
    }


    public void registerMouseAction() {
        Shape shape = (Shape)getNode();
        shape.setOnMouseDragged(e -> {
            if(getVel()== Point2D.ZERO) {
                iL.setStart(getPos());
                iL.setEndX(e.getSceneX());
                iL.setEndY(e.getSceneY());
                iL.addToGroup();
            }else{
                iL.removeFromGroup();
            }
        });
        shape.setOnMouseReleased(e -> {
            if(iL.getIsInGroup()){
                iL.removeFromGroup();
                setVel(iL.getStart().subtract(iL.getEnd()).multiply(App.HIT_FORCE));
            }
        });
    }
}
