package checkers;


import javafx.scene.shape.Ellipse;

public abstract class Piece extends Ellipse {
    //in board tiles
    int x;
    int y;
    // in pixels
    double mouseX;
    double mouseY;
    int validDirection;

    // State template will change how far can a piece move depending on it`s type.
    protected State currentState;

    public boolean move(int newX, int newY) {
        relocate(newX * Board.tileSize, newY * Board.tileSize);
        x = newX;
        y = newY;
        return true;
    }

    public boolean movingPossible(int newX, int newY) {
        return currentState.movingPossible(x, y, newX, newY, validDirection);
    }

    public int range() {
        return currentState.getRange();
    }

    public void cancelMoving() {
        relocate(x * Board.tileSize, y * Board.tileSize);
    }
}
