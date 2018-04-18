package checkers;

import javafx.scene.Group;

public class ProtectedBoard extends Board {
    Board controlledBoard = new SimpleBoard();

    @Override
    public Group tilesToGroup() {
        return controlledBoard.tilesToGroup();
    }

    @Override
    public Group piecesToGroup() {
        return controlledBoard.piecesToGroup();
    }

    @Override
    public Tile getTile(int x, int y) {
        return controlledBoard.getTile(x, y);
    }

    @Override
    public Piece getPiece(int x, int y) {
        return controlledBoard.getPiece(x, y);
    }

    @Override
    public void setActivePiece(int x, int y) {
        controlledBoard.setActivePiece(x, y);
    }

    @Override
    public Piece getActivePiece() {
        return controlledBoard.getActivePiece();
    }

    @Override
    public void relocateActivePiece(int x, int y) {
//        Tile newPlace = getTile(x, y);
//
//        if (activePiece != null) {
//            Tile oldPlace = getTile(activePiece.x, activePiece.y);
//
//            if (newPlace.getPiece() != null) {
//                activePiece.cancelMoving();
//                return;
//            }
//            if (activePiece.move(x, y)) {
//                oldPlace.setPiece(null);
//                newPlace.setPiece(activePiece);
//            } else {
//                activePiece.cancelMoving();
//            }
//            unsetActivePiece();
//        }

        Tile newPlace = getTile(x, y);
        Tile oldPlace = getTile(getActivePiece().x, getActivePiece().y);

        if (controlledBoard.getActivePiece() == null) {
            getActivePiece().cancelMoving();
            return;
        }
        if (!getActivePiece().movingPossible(x, y)) {
            getActivePiece().cancelMoving();
            return;
        }
        if (newPlace.getPiece() != null) {
            getActivePiece().cancelMoving();
            return;
        }
        controlledBoard.relocateActivePiece(x, y);
    }

    @Override
    public void unsetActivePiece() {
        controlledBoard.unsetActivePiece();
    }
}
