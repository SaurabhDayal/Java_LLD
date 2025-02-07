package aExercise.ticTacToe.strategies.winningstrategy;

import aExercise.ticTacToe.models.Board;
import aExercise.ticTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
