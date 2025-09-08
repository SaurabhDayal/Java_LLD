package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class RookMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {

        int startRow = startCell.getRow();
        int startCol = startCell.getCol();
        int endRow = endCell.getRow();
        int endCol = endCell.getCol();

        // Must move in straight line
        if (startRow != endRow && startCol != endCol) {
            return false;
        }

        // Check path is clear
        if (startRow == endRow) { // horizontal
            int step = startCol < endCol ? 1 : -1;
            for (int col = startCol + step; col != endCol; col += step) {
                if (board.getCell(startRow, col).getPiece() != null) {
                    return false;
                }
            }
        } else { // vertical
            int step = startRow < endRow ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                if (board.getCell(row, startCol).getPiece() != null) {
                    return false;
                }
            }
        }

        return true;
    }
}
