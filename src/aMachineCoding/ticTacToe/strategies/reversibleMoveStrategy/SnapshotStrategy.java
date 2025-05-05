package aMachineCoding.ticTacToe.strategies.reversibleMoveStrategy;

import aMachineCoding.ticTacToe.models.Cell;
import aMachineCoding.ticTacToe.models.Game;

import java.util.List;

public class SnapshotStrategy implements ReversibleMoveStrategy {

    @Override
    public void onMove(Game game) {
        // Save a deep snapshot of the board before the move
        game.saveBoardSnapshot();
    }

    @Override
    public void undo(Game game) {
        // Restore the most recent snapshot
        List<List<List<Cell>>> history = game.getStateHistory();
        if (!history.isEmpty()) {
            List<List<Cell>> lastSnapshot = history.remove(history.size() - 1);
            game.restoreBoardSnapshot(lastSnapshot);
        }
    }

    @Override
    public boolean shouldSaveBeforeMove() {
        return true;
    }
}
