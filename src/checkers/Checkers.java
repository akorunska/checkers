package checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkers extends Application {
    Board board = new ProtectedBoard();
    ActivePlayer activePlayer = new ActivePlayer();
    WinnerAnnouncer winnerAnnouncer;

//    Checkers () {
//        board = new ProtectedBoard();
//        activePlayer = new ActivePlayer(board);
//    }

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

        winnerAnnouncer = new WinnerAnnouncer(gameModeInfo());

        root.setOnMousePressed(e -> {
            int x = (int) e.getSceneX() / Board.tileSize;
            int y = (int) e.getSceneY() / Board.tileSize;

            board.setActivePiece(x, y);
        });

        root.setOnMouseReleased(e -> {
            int x = (int) e.getSceneX() / Board.tileSize;
            int y = (int) e.getSceneY() / Board.tileSize;

            Piece killed = activePlayer.handleMove(board, x, y);
            if (killed != null) {
                piecesGroup.getChildren().remove(killed);
            }
            if (winnerAnnouncer.gameIsOver(board.getBoardContent())) {
                winnerAnnouncer.showGameOverDialog(board.getBoardContent());

            }
        });

        return root;
    }

    public RulesType gameModeInfo() {
        List<String> choices = new ArrayList<>();
        choices.add("Classic Rules");
        choices.add("Poddavki");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Welcome to the checkers!");
        dialog.setContentText("Choose the type of rules:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String chosenType = result.get();
            if (chosenType.equals("Poddavki")) {
                dialog.close();
                return new PoddavkiRulesType();
            }
        }
        dialog.close();
        return new ClassicRulesType();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
