package checkers;

import javafx.scene.paint.Color;

public class LightPiece extends Piece {
    LightPiece(int initialX, int initialY) {
        validDirection = -1;
        x = initialX;
        y = initialY;
        currentState = new ManState();

        setRadiusX(Board.tileSize * 0.45);
        setRadiusY(Board.tileSize * 0.3);
        relocate(initialX * Board.tileSize, initialY * Board.tileSize);
        setTranslateX((Board.tileSize - Board.tileSize * 0.45 * 2) / 2);
        setTranslateY((Board.tileSize - Board.tileSize * 0.3 * 2) / 2);

        setFill(Color.valueOf("#FFFFFF"));

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + x * Board.tileSize,
                    e.getSceneY() - mouseY + y * Board.tileSize);
        });
    }

    @Override
    public boolean isEnemyPiece(Piece p) {
        return p instanceof DarkPiece;
    }

    @Override
    protected void updateToKing() {
        setFill(Color.valueOf("#EDE625"));
    }
}
