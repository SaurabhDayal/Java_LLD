package aMachineCoding.chessGame.models;

import aMachineCoding.chessGame.factories.Piece;

public class Cell {

    private final int row;
    private final int col;
    private String label;
    private Piece piece;

    // constructor
    public Cell(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    // returns the current piece on the cell
    public Piece getPiece() {
        return piece;
    }

    // puts a piece on the cell
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
