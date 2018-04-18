package checkers;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    final static int heigth = 8;
    final static int width = 8;
    final static int tileSize = 80;

    protected Piece activePiece;

    protected List<Tile> tiles = new ArrayList<Tile>();
    protected List<Piece> pieces = new ArrayList<Piece>();

    public abstract Group tilesToGroup();

    public abstract Group piecesToGroup();

    public abstract Tile getTile(int x, int y);

    public abstract Piece getPiece(int x, int y);

    public abstract void setActivePiece(int x, int y);

    public abstract Piece getActivePiece();

    public abstract void relocateActivePiece(int x, int y);

    public abstract void unsetActivePiece();
}
