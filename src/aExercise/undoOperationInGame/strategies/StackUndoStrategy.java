package aExercise.undoOperationInGame.strategies;

import aExercise.undoOperationInGame.model.Move;
import aExercise.undoOperationInGame.model.TicTacToe;

public class StackUndoStrategy implements UndoStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            game.moveStack.push(new Move(row, col, game.currentPlayer));
            game.board[row][col] = game.currentPlayer.toString();
            game.currentPlayer = (game.currentPlayer == TicTacToe.Player.X) ? TicTacToe.Player.O : TicTacToe.Player.X;
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (!game.moveStack.isEmpty()) {
            Move move = game.moveStack.pop();
            game.board[move.row][move.col] = "_";
            game.currentPlayer = move.player;
        }
    }
}
