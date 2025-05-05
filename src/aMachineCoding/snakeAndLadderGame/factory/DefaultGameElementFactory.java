package aMachineCoding.snakeAndLadderGame.factory;


import aMachineCoding.snakeAndLadderGame.element.GameElement;
import aMachineCoding.snakeAndLadderGame.element.Ladder;
import aMachineCoding.snakeAndLadderGame.element.Snake;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameElementFactory implements GameElementFactory {
    public List<GameElement> createGameElements() {
        List<GameElement> elements = new ArrayList<>();
        elements.add(new Snake(14, 7));
        elements.add(new Snake(31, 26));
        elements.add(new Ladder(3, 22));
        elements.add(new Ladder(8, 30));
        // Add more elements as needed
        return elements;
    }
}
