package checkers;

public class PieceCreator {
    public Piece factoryMethod(int x, int y) {
        if ((x + y) % 2 != 0) {
            if (y >= 0 && y <= 2)
                return new DarkPiece(x, y);
            if (y >= 5 && y <= 7)
                return new LightPiece(x, y);
            return null;
        }
        return null;
    }
}
