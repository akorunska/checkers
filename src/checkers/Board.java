package checkers;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    final static int heigth = 8;
    final static int width = 8;
    final static int tileSize = 80;

    public boolean lastOperationStatus;
    protected Piece activePiece;
    protected BoardContent boardContent;

    public abstract Group tilesToGroup();

    public abstract Group piecesToGroup();

    public abstract BoardContent getBoardContent();

    public abstract void setActivePiece(int x, int y);

    public abstract Piece getActivePiece();

    public abstract Piece relocateActivePiece(int x, int y);

    public abstract void unsetActivePiece();
}
