package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.Move;
import aMachineCoding.undoOperationInGame.model.Player;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void undoMove(TicTacToe game) {
        // Option 1: Direct Undo
//        if (!game.moveStack.isEmpty()) {
//            Move move = game.moveStack.pop();                           // Retrieve the last move from the stack
//            game.board[move.row][move.col] = "_";                       // Clear the cell
//            game.currentPlayer = move.player;                           // Restore the player who made the move
//        }

        // Option 2: Replay Undo
        if (!game.moveStack.isEmpty()) {
            // 1. Remove the last move
            game.moveStack.pop();

            // 2. Reset the board
            for (int i = 0; i < game.board.length; i++) {
                Arrays.fill(game.board[i], "_");
            }

            // 3. Replay remaining moves
            List<Move> moves = new ArrayList<>(game.moveStack);
            Collections.reverse(moves); // because stack is LIFO, reverse for correct order

            game.currentPlayer = Player.X; // reset to first player
            for (Move move : moves) {
                game.board[move.row][move.col] = move.player.toString();
                game.switchPlayer();
            }
        }
    }
}
