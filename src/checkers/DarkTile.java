package checkers;

import javafx.scene.paint.Color;

public class DarkTile extends Tile {
    @Override
    boolean setPiece(Piece p) {
        if (piece != null && p == null) {
            piece = null;
            return true;
        } else if (piece == null && p != null) {
            piece = p;
            return true;
        }
        return false;
    }

    @Override
    Piece getPiece() {
        return piece;
    }

    DarkTile(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;

        setWidth(Board.tileSize);
        setHeight(Board.tileSize);

        relocate(x * Board.tileSize, y * Board.tileSize);

        setFill(Color.valueOf("#26724C"));

        setOnMousePressed(e -> {
            setFill(Color.valueOf("#105030"));
        });

        setOnMouseReleased(e -> {
            setFill(Color.valueOf("#26724C"));
        });
    }
}
