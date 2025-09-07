package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class PawnMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
