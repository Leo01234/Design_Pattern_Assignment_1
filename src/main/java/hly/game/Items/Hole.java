package hly.game.Items;

import hly.game.App;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Leo01234
 * @version 1.0
 */
public class Hole implements Drawable{
    private ObservableList<Node> group;
    private boolean isInGroup = false;
    //include properties: xPos, yPos, radius
    private final Circle shape;

    public Hole(double xPos,double yPos) {
        this.shape = new Circle(xPos,yPos, App.HOLE_RADIUS);
        this.shape.setFill(Color.valueOf("black"));
    }
    public Point2D getPos(){
        return new Point2D(this.shape.getCenterX(),this.shape.getCenterY());
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
