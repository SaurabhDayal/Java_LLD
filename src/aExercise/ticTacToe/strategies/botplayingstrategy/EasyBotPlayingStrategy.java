package aExercise.ticTacToe.strategies.botplayingstrategy;

import aExercise.ticTacToe.models.Board;
import aExercise.ticTacToe.models.Cell;
import aExercise.ticTacToe.models.CellState;
import aExercise.ticTacToe.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        //Iterate over the board and make a move at the first empty cell.

        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
