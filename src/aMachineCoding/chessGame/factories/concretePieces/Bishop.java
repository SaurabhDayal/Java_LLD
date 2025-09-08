package aMachineCoding.chessGame.factories.concretePieces;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.BishopMovementStrategy;


public class Bishop extends Piece {

    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy(), isWhitePiece ? 'B' : 'b');
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
