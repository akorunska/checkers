package checkers;

// concrete tiles created in factory would be either LightTile of DarkTile

import javafx.scene.shape.Rectangle;

public abstract class Tile extends Rectangle {
    protected int x;
    protected int y;
    protected Piece piece;

    abstract boolean setPiece(Piece p);
    abstract Piece getPiece();
    abstract void lightBackgroundOn();
    abstract void lightBackgroundOff();
}
