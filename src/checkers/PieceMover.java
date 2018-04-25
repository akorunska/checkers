package checkers;

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

    public boolean movesExist(BoardContent boardContent, Piece toCheck) {
        return move.movesExist(boardContent, toCheck) || kill.movesExist(boardContent, toCheck);
    }

    public boolean killMovesExist(BoardContent boardContent, Piece toCheck) {
        return kill.movesExist(boardContent, toCheck);
    }
}


interface PieceCommand {
    void tryMoving(BoardContent boardContent, Piece killer, Tile t);
    boolean movesExist(BoardContent boardContent, Piece active);
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
        if (isValidMove(boardContent, active, t.x, t.y))
            candidates.put(t, null);
    }

    @Override
    public boolean movesExist(BoardContent boardContent, Piece active) {
        List<Tile> tiles = boardContent.getTiles();

        for (Tile t : tiles) {
            if (t.getPiece() != null)
                continue;
            if (!active.movingPossible(t.x, t.y))
                continue;
            if (isValidMove(boardContent, active, t.x, t.y))
                return true;
        }
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

    @Override
    public boolean movesExist(BoardContent boardContent, Piece active) {
        List<Tile> tiles = boardContent.getTiles();

        for (Tile t : tiles) {
            if (t.getPiece() != null)
                continue;
            if (t.x == active.x || t.y == active.y)
                continue;
            if (Math.abs(t.x - active.x) != Math.abs(t.y - active.y))
                continue;
            if (isValidKillMove(boardContent, active, t.x, t.y) != null)
                return true;
        }
        return false;
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