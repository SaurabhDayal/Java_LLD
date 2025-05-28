package aMachineCoding.ticTacToe.strategies.reversibleMoveStrategy;

import aMachineCoding.ticTacToe.models.Game;

public interface ReversibleMoveStrategy {

    void onMove(Game game);

    void undo(Game game);

    default boolean shouldSaveBeforeMove() {
        return false;
    }
}

