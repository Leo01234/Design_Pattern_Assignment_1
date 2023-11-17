package hly.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private static final String TITLE = "Bouncy Ball";
    private static final String CONFIG_PATH = "src/main/resources/config.json";
    private static final double FRAMETIME = 1.0/60.0;
    public static final double BALL_RADIUS = 15.0;
    public static final double FRICTION_ADJUST = 10.0;
    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game(CONFIG_PATH);

        Group root = new Group();
        Scene scene = new Scene(root,game.getWidth(),game.getHeight());

        stage.setResizable(false);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        // setup drawables
        game.addCanvas(root);
        game.addDrawables(root);

        // setup frames
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME), (actionEvent) -> game.tick());
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
