package aMachineCoding.chessGame.factories.concretePieces;

import aMachineCoding.chessGame.factories.Piece;
import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.RookMovementStrategy;

public class Rook extends Piece {

    public Rook(boolean isWhitePiece) {
        super(isWhitePiece, new RookMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
