package aExercise.undoOperationGame.strategies;

import aExercise.undoOperationGame.model.GameState;
import aExercise.undoOperationGame.model.TicTacToe;

public class VersioningUndoStrategy implements UndoStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            game.versionHistory.add(new GameState(game.board)); // Save current state
            game.board[row][col] = game.currentPlayer.toString(); // Update board
            game.switchPlayer(); // Change turn
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (game.versionHistory.size() > 1) { // Ensure at least one previous state exists
            GameState previousState = game.versionHistory.remove(game.versionHistory.size() - 1); // Get last state
            game.restoreBoard(previousState); // Restore board
            game.switchPlayer(); // Reverse turn
        }
    }
}
