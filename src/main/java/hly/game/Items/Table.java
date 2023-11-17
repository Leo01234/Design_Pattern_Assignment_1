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
public class Table implements Drawable{
    //include properties: xSize, ySize
    private final Rectangle shape;

    private double friction;
    public Table(long xSize, long ySize, String colour, double friction){
        this.shape = new Rectangle(xSize,ySize);
        this.shape.setFill(Color.valueOf(colour));
        this.friction=friction;
    }
    @Override
    public Node getNode() {
        return this.shape;
    }

    @Override
    public void addToGroup(ObservableList<Node> group) {
        group.add(this.shape);
    }
}
