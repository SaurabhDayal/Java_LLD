package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.Move;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class StackStrategy implements ReversibleMoveStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            game.moveStack.push(new Move(row, col, game.currentPlayer)); // Save move details to the stack
            game.board[row][col] = game.currentPlayer.toString();        // Place player's mark
            game.switchPlayer();                                         // Switch to the next player
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (!game.moveStack.isEmpty()) {
            Move move = game.moveStack.pop();                           // Retrieve the last move from the stack
            game.board[move.row][move.col] = "_";                       // Clear the cell
            game.currentPlayer = move.player;                           // Restore the player who made the move
        }
    }
}
