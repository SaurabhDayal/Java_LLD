package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.Delta;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class IncrementalStrategy implements ReversibleMoveStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            String previous = game.board[row][col];                                             // Store the previous value (should be "_")
            game.board[row][col] = game.currentPlayer.toString();                               // Place player's mark
            game.deltaStack.push(new Delta(row, col, previous, game.currentPlayer.toString())); // Save the delta (change) to the stack
            game.switchPlayer();                                                                // Switch to the next player
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (!game.deltaStack.isEmpty()) {
            Delta delta = game.deltaStack.pop();                 // Retrieve the last delta from the stack
            game.board[delta.row][delta.col] = delta.previous;   // Revert the cell to its previous value
            game.switchPlayer();                                 // Switch back to the previous player
        }
    }
}
