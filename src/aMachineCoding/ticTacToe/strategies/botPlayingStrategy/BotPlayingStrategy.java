package aMachineCoding.ticTacToe.strategies.botPlayingStrategy;

import aMachineCoding.ticTacToe.models.Board;
import aMachineCoding.ticTacToe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
