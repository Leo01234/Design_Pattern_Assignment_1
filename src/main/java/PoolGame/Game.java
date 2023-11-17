package PoolGame;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.List;

public class Game {

    Drawable rootDrawables = new DummyDrawable();
    List<Movable> movables = List.of();

    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        // TODO: Add drawable game objects to group calling Drawable::addToGroup(groupChildren)
        rootDrawables.addToGroup(groupChildren);
    }

    // tick() is called every frame, handle main game logic here
    public void tick() {
        // TODO: Implement game logic
        for (Movable movable : movables) {
            movable.move();
        }
    }
}

class DummyDrawable implements Drawable {

    @Override
    public Node getNode() {
        return null;
    }

    @Override
    public void addToGroup(ObservableList<Node> group) {}
}
