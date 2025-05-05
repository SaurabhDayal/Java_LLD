package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.GameState;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class VersioningStrategy implements ReversibleMoveStrategy {

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
