package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class BishopMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int startRow = startCell.getRow();
        int startCol = startCell.getCol();
        int endRow = endCell.getRow();
        int endCol = endCell.getCol();

        // Must move diagonally
        if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) return false;

        int rowStep = (endRow > startRow) ? 1 : -1;
        int colStep = (endCol > startCol) ? 1 : -1;

        int row = startRow + rowStep;
        int col = startCol + colStep;
        while (row != endRow && col != endCol) {
            if (board.getCell(row, col).getPiece() != null) return false;
            row += rowStep;
            col += colStep;
        }
        return true;
    }
}
