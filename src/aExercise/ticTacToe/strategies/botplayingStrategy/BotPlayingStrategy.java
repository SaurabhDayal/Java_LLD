package aExercise.ticTacToe.strategies.botplayingStrategy;

import aExercise.ticTacToe.models.Board;
import aExercise.ticTacToe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
