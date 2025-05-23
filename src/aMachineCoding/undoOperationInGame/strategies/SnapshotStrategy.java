package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.GameState;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class SnapshotStrategy implements ReversibleMoveStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            game.stateHistory.add(new GameState(game.board)); // Save current state before making a move
            game.board[row][col] = game.currentPlayer.toString(); // Place player's mark
            game.switchPlayer(); // Switch to the next player
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (!game.stateHistory.isEmpty()) {
            GameState previousState = game.stateHistory.remove(game.stateHistory.size() - 1);
            game.restoreBoard(previousState); // Corrected method call
            game.switchPlayer(); // Switch back to the previous player
        }
    }
}
