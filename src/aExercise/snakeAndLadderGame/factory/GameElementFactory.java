package aExercise.snakeAndLadderGame.factory;

import aExercise.snakeAndLadderGame.element.GameElement;

import java.util.List;

public interface GameElementFactory {
    List<GameElement> createGameElements();
}
