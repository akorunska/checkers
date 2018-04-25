package checkers;

import java.util.List;

public class PoddavkiRulesType implements RulesType {
    @Override
    public int getWinnerNum(BoardContent boardContent) {
        List<Piece> pieces  = boardContent.getPieces();
        PieceMover pieceMover = new PieceMover(null);
        int light = 0;
        int dark = 0;

        for (Piece p : pieces) {
            if (pieceMover.movesExist(boardContent, p)) {
                if (p.belongsToPlayer(1))
                    light++;
                else
                    dark++;
            }
        }

        if (light > 0 && dark == 0) {
            return 2;
        } else if (dark > 0 && light == 0) {
            return 1;
        }
        return -1;
    }
}
