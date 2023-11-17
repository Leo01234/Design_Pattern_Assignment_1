package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Leo01234
 * @version 1.0
 */

public class Main extends Application {
    private static final double KEY_FRAME_DURATION = 0.017;
    private static int dy = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Application initialisation code here
        Group root = new Group();
        Scene scene = new Scene(root);

        primaryStage.setWidth(600);
        primaryStage.setHeight(440);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFXDemo");
        primaryStage.show();

        Canvas canvas = new Canvas(600, 400);
        root.getChildren().add(canvas);

        Circle circle = new Circle(50, 50, 10);
        circle.setFill(Color.BLUE);
        root.getChildren().add(circle);

        Timeline animationLoop = new Timeline();
        animationLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(KEY_FRAME_DURATION),
                (actionEvent) -> {
                    //Your event handler logic
                    double yMin = circle.getBoundsInParent().getMinY();
                    double yMax = circle.getBoundsInParent().getMaxY();
                    if (yMin < 0 || yMax > canvas.getBoundsInLocal().getMaxY()) {
                        dy = dy * -1;
                    }
                    circle.setCenterY(circle.getCenterY() + dy);
                });
        animationLoop.getKeyFrames().add(frame);
        animationLoop.play();
    }


}