package checkers;

import java.util.List;

// Strategy
// ActivePlayer class will contain current player and switch it as the game goes.

public class ActivePlayer {
    Player active;
    Player passive;

    private static Player player1;
    private static Player player2;

    ActivePlayer() {
        player1 = new Player1();
        player2 = new Player2();

        active = player1;
        passive = player2;
    }

    public Piece handleMove(Board board, int x, int y) {
        Piece killed = active.handleInput(board, x, y);

        if (!board.lastOperationStatus)
            return null;

        if (killed == null) {
            switchPlayer(board);
        } else {
            passive.setPieceAsKilled(killed);
            System.out.printf("killed piece in %d %d\n", killed.x, killed.y);

            Piece killer = board.getBoardContent().getPiece(x, y);
            System.out.println("Killer is " + killer);
            PieceMover pieceMover = new PieceMover(null);

            if (pieceMover.killMovesExist(board.getBoardContent(), killer)) {
                System.out.println("i want move blood");
                active.initiateMove(board);
            } else {
                System.out.println("no more deaths today");
                switchPlayer(board);
            }
        }
        return killed;
    }

    public void switchPlayer(Board b) {
        active.endMove(b);
        passive.initiateMove(b);

        if (active == player1) {
            active = player2;
            passive = player1;
        } else {
            active = player1;
            passive = player2;
        }

        System.out.println("player switched");
    }
}


abstract class Player {
    int remainingPieces;

    Player() {
        remainingPieces = 12;
    }

    public abstract void initiateMove(Board board);

    public abstract void endMove(Board board);

    public void setPieceAsKilled(Piece p) {
        remainingPieces--;
    }

    public abstract Piece handleInput(Board board, int x, int y);
}

class Player1 extends Player {

    @Override
    public void initiateMove(Board board) {
        List<Piece> pieces = board.getBoardContent().getPieces();
        PieceMover pieceMover = new PieceMover(null);
        boolean killMoveOccurred = false;

        for (Piece p : pieces) {
            if (p.belongsToPlayer(1)) {
                if (pieceMover.killMovesExist(board.getBoardContent(), p)) {
                    p.setActive();
                    killMoveOccurred = true;
                }
            }
        }
        if (killMoveOccurred)
            return;
        for (Piece p : pieces) {
            if (p.belongsToPlayer(1)) {
                p.setActive();
            }
        }
    }

    @Override
    public void endMove(Board board) {
        List<Piece> pieces = board.getBoardContent().getPieces();

        for (Piece p : pieces) {
            if (p.belongsToPlayer(1)) {
                p.setUnactive();
            }
        }
    }

    @Override
    // todo: think of a way to make this method useful
    public Piece handleInput(Board board, int x, int y) {
        Piece killed = board.relocateActivePiece(x, y);
        return killed;
    }
}

class Player2 extends Player {

    @Override
    public void initiateMove(Board board) {
        List<Piece> pieces = board.getBoardContent().getPieces();
        PieceMover pieceMover = new PieceMover(null);
        boolean killMoveOccurred = false;

        for (Piece p : pieces) {
            if (p.belongsToPlayer(2)) {
                if (pieceMover.killMovesExist(board.getBoardContent(), p)) {
                    p.setActive();
                    killMoveOccurred = true;
                }
            }
        }
        if (killMoveOccurred)
            return;
        for (Piece p : pieces) {
            if (p.belongsToPlayer(2)) {
                p.setActive();
            }
        }
    }

    @Override
    public void endMove(Board board) {
        List<Piece> pieces = board.getBoardContent().getPieces();

        for (Piece p : pieces) {
            if (p.belongsToPlayer(2)) {
                p.setUnactive();
            }
        }
    }

    @Override
    public Piece handleInput(Board board, int x, int y) {
        Piece killed = board.relocateActivePiece(x, y);
        return killed;
    }
}