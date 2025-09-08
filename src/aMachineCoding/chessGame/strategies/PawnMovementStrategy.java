package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class PawnMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int startRow = startCell.getRow();
        int startCol = startCell.getCol();
        int endRow = endCell.getRow();
        int endCol = endCell.getCol();

        boolean isWhite = startCell.getPiece().isWhite();
        int direction = isWhite ? 1 : -1; // white moves "down" the board, black "up"

        // Standard move: 1 step forward
        if (startCol == endCol && endCell.getPiece() == null) {
            if (endRow - startRow == direction) return true;
            // First move: 2 steps forward
            if ((isWhite && startRow == 1) || (!isWhite && startRow == 6)) {
                if (endRow - startRow == 2 * direction
                        && board.getCell(startRow + direction, startCol).getPiece() == null) {
                    return true;
                }
            }
        }

        // Capture: diagonal by 1 step
        if (Math.abs(startCol - endCol) == 1 && endRow - startRow == direction) {
            return endCell.getPiece() != null && endCell.getPiece().isWhite() != isWhite;
        }

        return false;
    }
}
