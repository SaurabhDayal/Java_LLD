package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.TicTacToe;

public interface ReversibleMoveStrategy {
    void makeMove(TicTacToe game, int row, int col);

    void undo(TicTacToe game);
}
