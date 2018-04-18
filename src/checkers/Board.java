package checkers;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Board {
    final static int heigth = 8;
    final static int width = 8;
    final static int tileSize = 80;

    List<Tile> tiles = new ArrayList<Tile>();
    List<Piece> pieces = new ArrayList<Piece>();


    Board() {
        TileCreator creator = new TileCreator();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles.add(creator.factoryMethod(x, y));
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
}
