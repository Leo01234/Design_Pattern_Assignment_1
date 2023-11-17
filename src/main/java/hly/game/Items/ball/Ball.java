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

    @Override
    public Node getNode() {
        return this.shape;
    }

    @Override
    public void addToGroup(ObservableList<Node> group) {
        group.add(this.shape);
    }

    @Override
    public double getXPos() {
        return this.shape.getCenterX();
    }

    @Override
    public double getYPos() {
        return this.shape.getCenterY();
    }
    public double getRadius(){
        return this.shape.getRadius();
    }

    @Override
    public double getXVel() {
        return this.velocity.getX();
    }

    @Override
    public double getYVel() {
        return this.velocity.getY();
    }
    public double getMass() {
        return this.mass;
    }

    @Override
    public void setXPos(double xPos) {
        this.shape.setCenterX(xPos);
    }

    @Override
    public void setYPos(double yPos) {
        this.shape.setCenterY(yPos);
    }
    public void setRadius(double radius){
        this.shape.setRadius(radius);
    }

    @Override
    public void setXVel(double xVel) {
        this.velocity = new Point2D(xVel, getYVel());
    }

    @Override
    public void setYVel(double yVel) {
        this.velocity= new Point2D(getXVel(),yVel);
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public void move() {
        double xPos = getXPos() + getXVel();
        double yPos = getYPos() + getYVel();
        setXPos(xPos);
        setYPos(yPos);
    }
}
