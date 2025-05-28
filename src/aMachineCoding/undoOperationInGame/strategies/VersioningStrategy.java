package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.GameState;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class VersioningStrategy implements ReversibleMoveStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            game.versionHistory.add(new GameState(game.board));        // Save the current board state to the version history
            game.board[row][col] = game.currentPlayer.toString();      // Place player's mark
            game.switchPlayer();                                       // Switch to the next player
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (game.versionHistory.size() > 1) {                          // Ensure there's a previous version to revert to
            GameState previousState = game.versionHistory.remove(game.versionHistory.size() - 1); // Retrieve the last saved state
            game.restoreBoard(previousState);                          // Restore the board to previous state
            game.switchPlayer();                                       // Switch back to the previous player
        }
    }
}
