package aMachineCoding.ticTacToe.strategies.winningStrategy;

import aMachineCoding.ticTacToe.models.Board;
import aMachineCoding.ticTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
