package hly.game.Items.ball;

import hly.game.Items.Drawable;
import hly.game.Items.Movable;
import hly.game.strategy.BallInHoleStrategy;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public abstract class Ball implements Movable, Drawable {
    private ObservableList<Node> group;
    private boolean isInGroup = false;

    private Point2D initPos;
    //include properties: xPos, yPos, radius
    private final Circle shape;
    private Point2D velocity = Point2D.ZERO;
    private double mass;
    private BallInHoleStrategy strategy;

    public Ball() {
        this.shape = new Circle();
    }

    public abstract void setColour();

    public void ballInHole() {
        strategy.execute(this);
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

    public void setInitPos(Point2D initPos) {
        this.initPos = initPos;
    }

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

    public void setStrategy(BallInHoleStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void move() {
        if(getVel()!= Point2D.ZERO) {
            setPos(getPos().add(getVel()));
        }
    }
    public void resetPos(){
        setPos(initPos);
    }
}
