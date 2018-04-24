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

        // checking if we should change state to super-privileged King
        if ((validDirection == -1 && y == 0) || validDirection == 1 && y == Board.heigth - 1) {
            currentState = new KingState();
            updateToKing();
        }
        return true;
    }

    public boolean movingPossible(int newX, int newY) {
        return currentState.movingPossible(x, y, newX, newY, validDirection);
    }

    abstract public boolean isEnemyPiece (Piece p);

    abstract protected void updateToKing();

    public int range() {
        return currentState.getRange();
    }

    public void cancelMoving() {
        relocate(x * Board.tileSize, y * Board.tileSize);
    }
}

class ManState implements State {

    @Override
    public boolean movingPossible(int curX, int curY, int newX, int newY, int dir) {
        if (curX == newX || curY == newY)
            return false;
        if (Math.abs(curX - newX) != Math.abs(curY - newY))
            return false;
        // checking if direction is correct to the piece color
        // as pieces cannot move backwards
        if ((newY - curY) / Math.abs(newY - curY) != dir)
            return false;
        return true;
    }

    @Override
    public int getRange() {
        return 1;
    }
}

class KingState implements State {

    @Override
    public boolean movingPossible(int curX, int curY, int newX, int newY, int dir) {
        if (curX == newX || curY == newY)
            return false;
        if (Math.abs(curX - newX) != Math.abs(curY - newY))
            return false;
        return true;
    }

    @Override
    public int getRange() {
        return 6;
    }
}
