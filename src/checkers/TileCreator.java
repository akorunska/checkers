package checkers;

public class TileCreator {
    public Tile factoryMethod(int x, int y){
        if (x < 0 || x >= Board.width || y < 0 || y >= Board.heigth)
            return null;
        if ((x + y) % 2 == 0)
            return new LightTile(x, y);
        return new DarkTile(x, y);
    }
}
