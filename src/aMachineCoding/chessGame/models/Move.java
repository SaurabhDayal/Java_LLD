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
        if (endCell.getPiece() == null) return true;
        else return !(startCell.getPiece().isWhite() == endCell.getPiece().isWhite());
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
