package aMachineCoding.chessGame.strategies;

import aMachineCoding.chessGame.models.Board;
import aMachineCoding.chessGame.models.Cell;

public class QueenMovementStrategy implements MovementStrategy {
    private final RookMovementStrategy rookStrategy = new RookMovementStrategy();
    private final BishopMovementStrategy bishopStrategy = new BishopMovementStrategy();

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return rookStrategy.canMove(board, startCell, endCell) || bishopStrategy.canMove(board, startCell, endCell);
    }
}
