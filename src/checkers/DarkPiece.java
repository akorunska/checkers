package checkers;

import javafx.scene.paint.Color;

public class DarkPiece extends Piece{
    DarkPiece(int initialX, int initialY) {
        validDirection = 1;
        x = initialX;
        y = initialY;
        currentPieceStrategy = new ManPieceStrategy();
        isActive = false;

        setRadiusX(Board.tileSize * 0.45);
        setRadiusY(Board.tileSize * 0.3);
        relocate(initialX * Board.tileSize, initialY * Board.tileSize);
        setTranslateX((Board.tileSize - Board.tileSize * 0.45 * 2) / 2);
        setTranslateY((Board.tileSize - Board.tileSize * 0.3 * 2) / 2);

        setFill(Color.valueOf("#123515"));

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            if (isActive) {
                relocate(e.getSceneX() - mouseX + x * Board.tileSize,
                        e.getSceneY() - mouseY + y * Board.tileSize);
            }
        });
    }

    @Override
    public boolean isEnemyPiece(Piece p) {
        return p instanceof LightPiece;
    }

    @Override
    public boolean belongsToPlayer(int playerNum) {
        if (playerNum == 2)
            return true;
        return false;
    }

    @Override
    protected void updateToKing() {
        setFill(Color.valueOf("#03100B"));
    }
}
