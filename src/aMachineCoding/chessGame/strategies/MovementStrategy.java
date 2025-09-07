package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
