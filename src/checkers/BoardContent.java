package checkers;

import java.util.ArrayList;
import java.util.List;

public class BoardContent {
    protected List<Tile> tiles = new ArrayList<Tile>();
    protected List<Piece> pieces = new ArrayList<Piece>();

    BoardContent() {
        TileCreator tileCreator = new TileCreator();
        PieceCreator pieceCreator = new PieceCreator();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = tileCreator.factoryMethod(x, y);
                if (t != null)
                    tiles.add(t);

                Piece p = pieceCreator.factoryMethod(x, y);
                t.setPiece(p);
                if (p != null)
                    pieces.add(p);
            }
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Tile getTile(int x, int y) {
        for (Tile t : tiles) {
            if (t.x == x  && t.y == y)
                return t;
        }
        return null;
    }

    public Piece getPiece(int x, int y) {
        Tile t = getTile(x, y);

        return t.getPiece();
    }

    public void removePiece(Piece p) {
        pieces.remove(p);
    }
}
