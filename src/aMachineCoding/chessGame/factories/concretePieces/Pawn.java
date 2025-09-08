package aMachineCoding.chessGame.factories.concretePieces;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.PawnMovementStrategy;

public class Pawn extends Piece {

    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy(), isWhitePiece ? 'P' : 'p');
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
