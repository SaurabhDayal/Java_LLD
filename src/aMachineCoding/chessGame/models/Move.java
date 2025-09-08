package aMachineCoding.chessGame.models;

public class Move {

    private final Cell startCell;
    private final Cell endCell;

    // Constructor to initialize the move with start and end cells
    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    // Validate if the move is valid
    public boolean isValid() {
        // Case 1: If the destination cell is empty, the move is valid
        if (endCell.getPiece() == null) {
            return true;
        }

        // Case 2: If the destination cell already has a piece,
        // then check the color of both pieces.
        boolean isStartWhite = startCell.getPiece().isWhite();  // color of the moving piece
        boolean isEndWhite = endCell.getPiece().isWhite();      // color of the target piece

        // If the two pieces are of the same color, move is invalid
        // Otherwise, the pieces are of opposite colors, so capturing is valid
        return isStartWhite != isEndWhite;
    }

    // gets the start cell
    public Cell getStartCell() {
        return startCell;
    }

    // gets the end cell
    public Cell getEndCell() {
        return endCell;
    }
}
