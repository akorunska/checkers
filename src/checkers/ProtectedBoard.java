package checkers;

import javafx.scene.Group;
import java.util.*;

// Proxy, that adds additional checks and logic over a SimpleBoard

public class ProtectedBoard extends Board {
    private Board controlledBoard = new SimpleBoard();
    Map<Tile, Piece> candidates = new HashMap<>();
    // dictionary, where values are killed pieces
    PieceMover pieceMover = new PieceMover(candidates);

    @Override
    public Group tilesToGroup() {
        return controlledBoard.tilesToGroup();
    }

    @Override
    public Group piecesToGroup() {
        return controlledBoard.piecesToGroup();
    }

    @Override
    public BoardContent getBoardContent() {
        return controlledBoard.boardContent;
    }

    @Override
    public void setActivePiece(int x, int y) {
        if (controlledBoard.boardContent.getPiece(x, y) == null)
            return;
        if (!controlledBoard.boardContent.getPiece(x, y).isActive)
            return;
        controlledBoard.setActivePiece(x, y);
        pickCandidates();

        Set<Tile> tiles = candidates.keySet();
        for (Tile t : tiles) {
            t.lightBackgroundOn();
        }
    }

    @Override
    public Piece getActivePiece() {
        return controlledBoard.getActivePiece();
    }

    protected void pickCandidates() {
        if (getActivePiece() != null) {
            Piece oldPlace = getActivePiece();
            List <Tile> tiles = controlledBoard.boardContent.getTiles();

            for (Tile t: tiles) {
                // calling kill via PieceMover
                pieceMover.tryKilling(controlledBoard.boardContent, oldPlace, t);
            }
            if (!candidates.isEmpty())
                return;
            for (Tile t: tiles) {
                // calling move via PieceMover
                pieceMover.tryMoving(controlledBoard.boardContent, oldPlace, t);
            }
        }
    }

    private void killPiece(Piece killed) {
        if (killed == null)
            return;
        Tile homeTile = controlledBoard.boardContent.getTile(killed.x, killed.y);

        homeTile.setPiece(null);
        controlledBoard.boardContent.removePiece(killed);
    }

    @Override
    public Piece relocateActivePiece(int x, int y) {
        Set<Tile> tiles = candidates.keySet();
        Piece killed = null;

        for (Tile t: tiles) {
            if (t.x == x && t.y == y) {
                killed = candidates.get(t);
                controlledBoard.relocateActivePiece(x, y);
                unsetActivePiece();
                killPiece(killed);
                lastOperationStatus = true;
                return killed;
            }
        }
        lastOperationStatus = false;
        if (controlledBoard.getActivePiece() != null) {
            controlledBoard.getActivePiece().cancelMoving();
            unsetActivePiece();
        }
        return null;
    }

    @Override
    public void unsetActivePiece() {
        controlledBoard.unsetActivePiece();
        Set<Tile> tiles = candidates.keySet();
        for (Tile t : tiles) {
            t.lightBackgroundOff();
        }
        candidates.clear();
    }
}
