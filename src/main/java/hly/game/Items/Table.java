package hly.game.Items;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Leo01234
 * @version 1.0
 */
public class Table implements Drawable {
    private ObservableList<Node> group;
    private boolean isInGroup = false;
    //include properties: xSize, ySize
    private final Rectangle shape;

    private double friction;

    public Table(long xSize, long ySize, String colour, double friction) {
        this.shape = new Rectangle(xSize, ySize);
        this.shape.setFill(Color.valueOf(colour));
        this.friction = friction;
    }

    public double getXSize() {
        return shape.getWidth();
    }


    public double getYSize() {
        return shape.getHeight();
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
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
