package hly.game.Items;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;

/**
 * @author Leo01234
 * @version 1.0
 */
public class IndicationLine implements Drawable{
    private ObservableList<Node> group;
    private boolean isInGroup = false;
    private final Line shape;
    public IndicationLine(){
        this.shape=new Line();
    }
    public void setStart(Point2D start){
        this.shape.setStartX(start.getX());
        this.shape.setStartY(start.getY());
    }
    public Point2D getStart(){
        return new Point2D(this.shape.getStartX(),this.shape.getStartY());
    }
    public Point2D getEnd(){
        return new Point2D(this.shape.getEndX(),this.shape.getEndY());
    }
    public void setEndX(double endX){
        this.shape.setEndX(endX);
    }
    public void setEndY(double endY){
        this.shape.setEndY(endY);
    }
    @Override
    public Node getNode() {
        return this.shape;
    }

    @Override
    public void setGroup(ObservableList<Node> group) {
        this.group = group;
    }

    @Override
    public ObservableList<Node> getGroup() {
        return group;
    }

    @Override
    public void addToGroup() {
        if (!isInGroup) {
            this.group.add(this.shape);
            isInGroup=true;
        }
    }

    @Override
    public void removeFromGroup() {
        if(isInGroup) {
            this.group.remove(this.shape);
            isInGroup = false;
        }
    }

    @Override
    public boolean getIsInGroup() {
        return isInGroup;
    }
}
