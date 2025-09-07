package aMachineCoding.chessGame.factories.concretePieces;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.KingMovementStrategy;


public class King extends Piece {
    public King(boolean isWhitePiece) {
        super(isWhitePiece, new KingMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
