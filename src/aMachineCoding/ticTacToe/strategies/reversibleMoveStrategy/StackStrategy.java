package aMachineCoding.ticTacToe.strategies.reversibleMoveStrategy;

import aMachineCoding.ticTacToe.models.Cell;
import aMachineCoding.ticTacToe.models.CellState;
import aMachineCoding.ticTacToe.models.Game;
import aMachineCoding.ticTacToe.models.Move;

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
