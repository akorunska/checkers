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

    protected State currentState;

    public boolean move(int newX, int newY) {
        if (!movingPossible(newX, newY))
            return false;
        relocate(newX * Board.tileSize, newY * Board.tileSize);
        x = newX;
        y = newY;
        return true;
    }

    public boolean movingPossible(int newX, int newY) {
        return currentState.movingPossible(x, y, newX, newY, validDirection);
    }

    public void cancelMoving() {
        relocate(x * Board.tileSize, y * Board.tileSize);
    }
}
