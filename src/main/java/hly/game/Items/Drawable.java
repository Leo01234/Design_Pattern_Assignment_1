package hly.game.Items;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface Drawable {
    Node getNode();
    void setGroup(ObservableList<Node> group);
    ObservableList<Node> getGroup();
    // Implementations show recursively add their child drawables to group
    void addToGroup();

    void removeFromGroup();
    boolean getIsInGroup();
}
