package aMachineCoding.chessGame.factories.concretePieces;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.KnightMovementStrategy;


public class Knight extends Piece {

    public Knight(boolean isWhitePiece) {
        super(isWhitePiece, new KnightMovementStrategy(), isWhitePiece ? 'N' : 'n');
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
