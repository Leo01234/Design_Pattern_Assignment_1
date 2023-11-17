package hly.game.Items.ball;

import hly.game.Items.Drawable;
import hly.game.Items.Movable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public abstract class Ball implements Movable, Drawable {
    //include properties: xPos, yPos, radius
    private final Circle shape;
    private Point2D velocity = Point2D.ZERO;
    private double mass;

    public Ball() {
        this.shape = new Circle();
    }

    public abstract void setColour();
    @Override
    public Node getNode() {
        return this.shape;
    }

    @Override
    public void addToGroup(ObservableList<Node> group) {
        group.add(this.shape);
    }

    @Override
    public void removeFromGroup(ObservableList<Node> group) {
        group.remove(this.shape);
    }

    // =============getters================
    @Override
    public Point2D getPos(){
        return new Point2D(this.shape.getCenterX(),this.shape.getCenterY());
    }

    public double getRadius() {
        return this.shape.getRadius();
    }

    @Override
    public Point2D getVel() {
        return velocity;
    }

    public double getMass() {
        return this.mass;
    }

    // =============setters================
    @Override
    public void setPos(Point2D pos) {
        this.shape.setCenterX(pos.getX());
        this.shape.setCenterY(pos.getY());
    }

    public void setRadius(double radius) {
        this.shape.setRadius(radius);
    }

    @Override
    public void setVel(Point2D vel) {
        this.velocity = vel;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void reverseXVel(){
        this.velocity = new Point2D(-this.velocity.getX(),this.velocity.getY());
    }
    public void reverseYVel(){
        this.velocity = new Point2D(this.velocity.getX(),-this.velocity.getY());
    }
    @Override
    public void move() {
        if(getVel()!= Point2D.ZERO) {
            setPos(getPos().add(getVel()));
        }
    }
}
