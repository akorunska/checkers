package checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Checkers extends Application {
    Board board = new Board();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(setupContent());

        primaryStage.setTitle("Checkers by augustus tertius c:");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane setupContent() {
        Pane root = new Pane();

        root.setPrefSize(board.width * board.tileSize, board.heigth * board.tileSize);
        root.getChildren().add(board.tilesToGroup());

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
