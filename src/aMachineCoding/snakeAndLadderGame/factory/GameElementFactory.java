package aMachineCoding.snakeAndLadderGame.factory;

import aMachineCoding.snakeAndLadderGame.element.GameElement;

import java.util.List;

public interface GameElementFactory {
    List<GameElement> createGameElements();
}
