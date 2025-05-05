package aMachineCoding.undoOperationInGame.strategies;

import aMachineCoding.undoOperationInGame.model.Delta;
import aMachineCoding.undoOperationInGame.model.TicTacToe;

public class IncrementalStrategy implements ReversibleMoveStrategy {

    @Override
    public void makeMove(TicTacToe game, int row, int col) {
        if (game.board[row][col].equals("_")) {
            String previous = game.board[row][col];
            game.board[row][col] = game.currentPlayer.toString();
            game.deltaStack.push(new Delta(row, col, previous, game.currentPlayer.toString()));
            game.currentPlayer = (game.currentPlayer == TicTacToe.Player.X) ? TicTacToe.Player.O : TicTacToe.Player.X;
        }
    }

    @Override
    public void undo(TicTacToe game) {
        if (!game.deltaStack.isEmpty()) {
            Delta delta = game.deltaStack.pop();
            game.board[delta.row][delta.col] = delta.previous;
            game.currentPlayer = (game.currentPlayer == TicTacToe.Player.X) ? TicTacToe.Player.O : TicTacToe.Player.X;
        }
    }
}
