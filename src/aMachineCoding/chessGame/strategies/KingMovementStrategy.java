package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class KingMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.getRow() - endCell.getRow());
        int colDiff = Math.abs(startCell.getCol() - endCell.getCol());

        // Can move one step in any direction
        return rowDiff <= 1 && colDiff <= 1;
    }
}
