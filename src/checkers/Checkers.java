package checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Checkers extends Application {
    Board board = new ProtectedBoard();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(setupContent());

        primaryStage.setTitle("Checkers by augustus tertius c:");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane setupContent() {
        Pane root = new Pane();

        root.setPrefSize(Board.width * Board.tileSize, Board.heigth * Board.tileSize);

        Group tilesGroup = board.tilesToGroup();
        Group piecesGroup = board.piecesToGroup();
        root.getChildren().add(tilesGroup);
        root.getChildren().add(piecesGroup);

        root.setOnMousePressed(e -> {
            int x = (int) e.getSceneX() / Board.tileSize;
            int y = (int) e.getSceneY() / Board.tileSize;

            board.setActivePiece(x, y);
        });

        root.setOnMouseReleased(e -> {
            int x = (int) e.getSceneX() / Board.tileSize;
            int y = (int) e.getSceneY() / Board.tileSize;

            Piece killed = board.relocateActivePiece(x, y);
            if (killed != null) {
                piecesGroup.getChildren().remove(killed);
            }
        });

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
