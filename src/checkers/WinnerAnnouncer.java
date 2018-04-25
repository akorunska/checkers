package checkers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class WinnerAnnouncer {
    // creates dialog window that announces winner
    // contains type of rules, that determines, who winner is
    RulesType rulesType;

    WinnerAnnouncer(RulesType r) {
        rulesType = r;
    }

    public boolean gameIsOver(BoardContent boardContent) {
        return (rulesType.getWinnerNum(boardContent) > 0);
    }

    public void showGameOverDialog(BoardContent boardContent){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Game is over");
        alert.setHeaderText("Game is over");

        int winnerNum = rulesType.getWinnerNum(boardContent);
        alert.setContentText("The winner is player " + winnerNum);

        ButtonType button = new ButtonType("Ok");

        alert.getButtonTypes().setAll(button);
        alert.showAndWait();
    }

}
