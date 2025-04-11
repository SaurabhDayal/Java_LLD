package aExercise.ticTacToe.strategies.reversibleMoveStrategy;

import aExercise.ticTacToe.models.Game;

public interface ReversibleMoveStrategy {
    void onMove(Game game);

    void undo(Game game);

    default boolean shouldSaveBeforeMove() {
        return false;
    }
}

