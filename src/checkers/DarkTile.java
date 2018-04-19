package checkers;

import javafx.scene.paint.Color;

public class DarkTile extends Tile {
    @Override
    boolean setPiece(Piece p) {
//        if (piece != null && p == null) {
//            piece = null;
//            return true;
//        } else if (piece == null && p != null) {
//            piece = p;
//            return true;
//        }
//        return false;
        piece = p;
        return true;
    }

    @Override
    Piece getPiece() {
        return piece;
    }

    @Override
    void lightBackgroundOn() {
        setFill(Color.valueOf("#2E6A4C"));
    }

    @Override
    void lightBackgroundOff() {
        setFill(Color.valueOf("#1A7E4C"));
    }

    DarkTile(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;

        setWidth(Board.tileSize);
        setHeight(Board.tileSize);

        relocate(x * Board.tileSize, y * Board.tileSize);

        setFill(Color.valueOf("#1A7E4C"));
    }
}
