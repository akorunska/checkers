package checkers;

public interface State {
    boolean movingPossible(int curX, int curY, int newX, int newY, int dir);
    int getRange();
}
