package checkers;

public class ManState implements State {

    @Override
    public boolean movingPossible(int curX, int curY, int newX, int newY, int dir) {
        if (Math.abs(curX - newX) != Math.abs(curY - newY))
            return false;
        // checking if direction is correct to the piece color
        // as pieces cannot move backwards
        if ((newY - curY) / Math.abs(newY - curY) != dir)
            return false;
        if (Math.abs(curX - newX) > 2 || Math.abs(curY - newY) > 2)
            return false;
        return true;
    }
}
