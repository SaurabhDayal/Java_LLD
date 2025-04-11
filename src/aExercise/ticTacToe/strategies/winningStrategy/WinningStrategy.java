package aExercise.ticTacToe.strategies.winningStrategy;

import aExercise.ticTacToe.models.Board;
import aExercise.ticTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
