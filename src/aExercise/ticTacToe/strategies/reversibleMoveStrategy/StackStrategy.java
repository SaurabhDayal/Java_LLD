package aExercise.ticTacToe.strategies.reversibleMoveStrategy;

import aExercise.ticTacToe.models.Cell;
import aExercise.ticTacToe.models.CellState;
import aExercise.ticTacToe.models.Game;
import aExercise.ticTacToe.models.Move;

public class StackStrategy implements ReversibleMoveStrategy {

    @Override
    public void onMove(Game game) {
        Move lastMove = game.getMoves().get(game.getMoves().size() - 1);
        game.getMoveStack().push(lastMove);
    }

    @Override
    public void undo(Game game) {
        if (!game.getMoveStack().isEmpty()) {
            Move last = game.getMoveStack().pop();
            Cell cell = last.getCell();
            cell.setCellState(CellState.EMPTY);
            cell.setPlayer(null);
        }
    }
}
