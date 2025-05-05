package aMachineCoding.ticTacToe.strategies.botPlayingStrategy;

import aMachineCoding.ticTacToe.models.Board;
import aMachineCoding.ticTacToe.models.Cell;
import aMachineCoding.ticTacToe.models.CellState;
import aMachineCoding.ticTacToe.models.Move;

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
