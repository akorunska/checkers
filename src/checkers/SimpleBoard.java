package checkers;

import javafx.scene.Group;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;
import java.util.List;

public class SimpleBoard extends Board{

    SimpleBoard() {
        boardContent = new BoardContent();
    }

    public Group tilesToGroup(){
        Group tilesGroup = new Group();
        List<Tile> tiles = boardContent.getTiles();

        for (Tile t : tiles) {
            tilesGroup.getChildren().add(t);
        }
        return tilesGroup;
    }

    public Group piecesToGroup(){
        Group piecesGroup = new Group();
        List<Piece> pieces = boardContent.getPieces();

        for (Piece p : pieces) {
            piecesGroup.getChildren().add(p);
        }
        return piecesGroup;
    }

    public void setActivePiece(int x, int y) {
        activePiece = boardContent.getPiece(x, y);
    }

    public Piece getActivePiece() {
        return activePiece;
    }

    public void relocateActivePiece(int x, int y) {
        Tile newPlace = boardContent.getTile(x, y);
        Tile oldPlace = boardContent.getTile(activePiece.x, activePiece.y);

        activePiece.move(x, y);
        oldPlace.setPiece(null);
        newPlace.setPiece(activePiece);
        unsetActivePiece();
    }

    public void unsetActivePiece() {
        activePiece = null;
    }
}
