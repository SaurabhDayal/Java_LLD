package aExercise.undoOperationInGame.strategies;

import aExercise.undoOperationInGame.model.TicTacToe;

public interface ReversibleMoveStrategy {
    void makeMove(TicTacToe game, int row, int col);

    void undo(TicTacToe game);
}
