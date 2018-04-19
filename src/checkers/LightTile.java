package checkers;

import javafx.scene.paint.Color;

public class LightTile extends Tile {
    @Override
    boolean setPiece(Piece p) {
        return false;
    }

    @Override
    Piece getPiece() {
        return null;
    }

    @Override
    void lightBackgroundOn() {

    }

    @Override
    void lightBackgroundOff() {

    }

    LightTile(int xCoord, int yCoord){
        x = xCoord;
        y = yCoord;

        setWidth(Board.tileSize);
        setHeight(Board.tileSize);

        relocate(x * Board.tileSize, y * Board.tileSize);

        setFill(Color.valueOf("#EDED95"));
    }
}
