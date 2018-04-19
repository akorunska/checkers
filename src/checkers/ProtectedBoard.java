package checkers;

import javafx.scene.Group;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.ArrayList;
import java.util.List;

// Proxy, that adds additional checks and logic over a SimpleBoard

public class ProtectedBoard extends Board {
    Board controlledBoard = new SimpleBoard();
    List<Tile> candidates = new ArrayList<Tile>();

    @Override
    public Group tilesToGroup() {
        return controlledBoard.tilesToGroup();
    }

    @Override
    public Group piecesToGroup() {
        return controlledBoard.piecesToGroup();
    }

    @Override
    public Tile getTile(int x, int y) {
        return controlledBoard.getTile(x, y);
    }

    @Override
    public Piece getPiece(int x, int y) {
        return controlledBoard.getPiece(x, y);
    }

    @Override
    public void setActivePiece(int x, int y) {
        controlledBoard.setActivePiece(x, y);
        pickCandidates();
        for (Tile t : candidates) {
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
            System.out.printf("now piece is in %d %d\n", oldPlace.x, oldPlace.y);


            for (Tile t: controlledBoard.tiles) {
                if (t.getPiece() != null)
                    continue;
                if (t.x == oldPlace.x || t.y == oldPlace.y)
                    continue;
                if (isValidKillMove(oldPlace, t.x, t.y))
                    candidates.add(t);
            }
            if (!candidates.isEmpty())
                return;
            for (Tile t: controlledBoard.tiles) {
                if (!getActivePiece().movingPossible(t.x, t.y))
                    continue;
                if (t.x == oldPlace.x || t.y == oldPlace.y)
                    continue;
                if (t.getPiece() != null)
                    continue;
                if (isValidMove(oldPlace, t.x, t.y) || isValidJumpMove(oldPlace, t.x, t.y))
                    candidates.add(t);
            }
        }
    }

    protected boolean isValidKillMove(Piece killer, int x, int y) {
        int xShift = (x - killer.x) / Math.abs(x - killer.x);
        int yShift = (y - killer.y) / Math.abs(y - killer.y);

        System.out.printf("checking %d %d\t\t", x, y);
        System.out.printf("xshift: %d, yshift: %d\n", xShift, yShift);

        int curX = killer.x + xShift;
        int curY = killer.y + yShift;
        boolean enemyOccurred = false;

        while (Math.abs(curX - x) <= killer.range() && curX != x && curY != y && !enemyOccurred) {
            Piece occurred = getPiece(curX, curY);
            if (occurred != null && occurred.validDirection == -killer.validDirection)
                enemyOccurred = true;
            curX += xShift;
            curY += yShift;
        }
        if (enemyOccurred)
            return true;
        return false;
    }

    protected boolean isValidJumpMove(Piece p, int x, int y) {
        int xShift = (x - p.x) / Math.abs(x - p.x);
        int yShift = (y - p.y) / Math.abs(y - p.y);

        int curX = p.x + xShift;
        int curY = p.y + yShift;
        boolean pieceOccured = false;

        while (curX != x && curY != y && Math.abs(curX - x) <= p.range() && !pieceOccured) {
            Piece occured = getPiece(curX, curY);
            if (occured != null)
                pieceOccured = true;
            curX += xShift;
            curY += yShift;
        }
        if (pieceOccured)
            return true;
        return false;
    }

    protected boolean isValidMove(Piece p, int x, int y){
        int xShift = (x - p.x) / Math.abs(x - p.x);
        int yShift = (y - p.y) / Math.abs(y - p.y);

        int curX = p.x + xShift;
        int curY = p.y + yShift;

        while (curX != x && curY != y && Math.abs(curX - x) <= p.range() - 1) {
            curX += xShift;
            curY += yShift;
        }
        if (curX == x && curY == y)
            return true;
        return false;
    }

    @Override
    public void relocateActivePiece(int x, int y) {
        for (Tile t: candidates) {
            if (t.x == x && t.y == y) {
                controlledBoard.relocateActivePiece(x, y);
                unsetActivePiece();
                return;
            }
        }
        controlledBoard.getActivePiece().cancelMoving();
        unsetActivePiece();
    }

    @Override
    public void unsetActivePiece() {
        controlledBoard.unsetActivePiece();
        for (Tile t : candidates) {
            t.lightBackgroundOff();
        }
        candidates.clear();
    }
}
