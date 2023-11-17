package hly.game.Items;

import javafx.geometry.Point2D;

public interface Movable {
    Point2D getPos();
    Point2D getVel();
    void setPos(Point2D pos);
    void setVel(Point2D vel);
    void move();
}
