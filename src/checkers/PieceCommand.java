package checkers;

public interface PieceCommand {
    void tryMoving(BoardContent boardContent, Piece killer, Tile t);
}
