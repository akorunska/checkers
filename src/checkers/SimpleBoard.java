package checkers;

import javafx.scene.Group;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;
import java.util.List;

public class SimpleBoard extends Board{

    SimpleBoard() {
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

    public Group tilesToGroup(){
        Group tilesGroup = new Group();

        for (Tile t : tiles) {
            tilesGroup.getChildren().add(t);
        }
        return tilesGroup;
    }

    public Group piecesToGroup(){
        Group piecesGroup = new Group();

        for (Piece p : pieces) {
            piecesGroup.getChildren().add(p);
        }
        return piecesGroup;
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

    public void setActivePiece(int x, int y) {
        activePiece = getPiece(x, y);
    }

    public Piece getActivePiece() {
        return activePiece;
    }

    public void relocateActivePiece(int x, int y) {
        Tile newPlace = getTile(x, y);
        Tile oldPlace = getTile(activePiece.x, activePiece.y);

        activePiece.move(x, y);
        if (oldPlace.setPiece(null))
            System.out.println("successully freed old tile");
        if (newPlace.setPiece(activePiece))
            System.out.println("sucessfully filled new tile");

        unsetActivePiece();
    }

    public void unsetActivePiece() {
        activePiece = null;
    }
}
