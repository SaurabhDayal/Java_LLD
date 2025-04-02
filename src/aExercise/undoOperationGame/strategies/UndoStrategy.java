package aExercise.undoOperationGame.strategies;

import aExercise.undoOperationGame.model.TicTacToe;

public interface UndoStrategy {
    void makeMove(TicTacToe game, int row, int col);

    void undo(TicTacToe game);
}
