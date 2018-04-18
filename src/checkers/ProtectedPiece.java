package checkers;

public class ProtectedPiece extends Piece {
    Piece controlledPiece;

    ProtectedPiece(Piece p) {
        controlledPiece = p;
        x = p.x;
        y = p.y;

        relocate(x * Board.tileSize, y * Board.tileSize);

    }

    @Override
    public boolean move(int newX, int newY) {
        if (Math.abs(newX - controlledPiece.x) != Math.abs(newY - controlledPiece.y))
            return false;

        return (controlledPiece.move(newX, newY));
    }
}
