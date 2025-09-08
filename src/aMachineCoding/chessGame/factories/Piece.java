package aMachineCoding.chessGame.factories;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;
import aMachineCoding.chessGame.strategies.MovementStrategy;

public abstract class Piece {

    private final boolean isWhitePiece; // is the piece white piece or black piece
    private final MovementStrategy movementStrategy;
    private boolean killed = false;
    private final char symbol; // single character symbol for display

    public Piece(boolean isWhitePiece, MovementStrategy movementStrategy, char symbol) {
        this.isWhitePiece = isWhitePiece;
        this.movementStrategy = movementStrategy;
        this.symbol = symbol;
    }

    public boolean isWhite() {
        return isWhitePiece;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean canMove(Board board, Cell startBlock, Cell endBlock) {
        return movementStrategy.canMove(board, startBlock, endBlock);
    }

    public char getSymbol() {
        return symbol;
    }
}
