package checkers;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class PieceMover {
    private PieceCommand move;
    private PieceCommand kill;

    PieceMover (Map <Tile, Piece> candidates) {
        move = new MoveCommand(candidates);
        kill = new KillCommand(candidates);
    }

    public void tryMoving (BoardContent boardContent, Piece active, Tile t) {
        move.tryMoving(boardContent, active, t);
    }

    public void tryKilling (BoardContent boardContent, Piece active, Tile t) {
        kill.tryMoving(boardContent, active, t);
    }
}


class MoveCommand implements PieceCommand {

    Map<Tile, Piece> candidates;

    MoveCommand(Map<Tile, Piece> c) {
        candidates = c;
    }

    @Override
    public void tryMoving(BoardContent boardContent, Piece active, Tile t) {
        if (!active.movingPossible(t.x, t.y))
            return;
        if (t.getPiece() != null)
            return;
        if (isValidMove(boardContent, active, t.x, t.y)
                || isValidJumpMove(boardContent,active, t.x, t.y))
//            candidates.add(t);
            candidates.put(t, null);
    }

    protected boolean isValidJumpMove(BoardContent boardContent, Piece p, int x, int y) {
        int xShift = (x - p.x) / Math.abs(x - p.x);
        int yShift = (y - p.y) / Math.abs(y - p.y);

        int curX = p.x + xShift;
        int curY = p.y + yShift;
        boolean pieceOccurred = false;

        while (curX != x && curY != y && Math.abs(curX - x) <= p.range() && !pieceOccurred) {
            Piece occurred = boardContent.getPiece(curX, curY);
            if (occurred != null)
                pieceOccurred = true;
            curX += xShift;
            curY += yShift;
        }
        if (pieceOccurred)
            return true;
        return false;
    }

    protected boolean isValidMove(BoardContent boardContent, Piece p, int x, int y){
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
}


class KillCommand implements PieceCommand {

    Map<Tile, Piece> candidates;

    KillCommand(Map<Tile, Piece> c) {
        candidates = c;
    }

    @Override
    public void tryMoving(BoardContent boardContent, Piece active, Tile t) {
        if (t.getPiece() != null)
            return;
        if (t.x == active.x || t.y == active.y)
            return;
        if (Math.abs(t.x - active.x) != Math.abs(t.y - active.y))
            return;

        Piece toBeKilled  = isValidKillMove(boardContent, active, t.x, t.y);
        // add the tile and the potentially killed piece to the dictionary
        if (toBeKilled != null) {
            candidates.put(t, toBeKilled);
        }
    }

    protected Piece isValidKillMove(BoardContent boardContent, Piece killer, int x, int y) {
        int xShift = (x - killer.x) / Math.abs(x - killer.x);
        int yShift = (y - killer.y) / Math.abs(y - killer.y);

        int curX = killer.x + xShift;
        int curY = killer.y + yShift;
        boolean enemyOccurred = false;
        Piece occurred = null;

        while (Math.abs(curX - x) <= killer.range() && curX != x && curY != y && !enemyOccurred) {
            occurred = boardContent.getPiece(curX, curY);
            if (occurred != null && killer.isEnemyPiece(occurred))
                enemyOccurred = true;
            curX += xShift;
            curY += yShift;
        }

        if (enemyOccurred)
            return occurred;
        return null;
    }
}