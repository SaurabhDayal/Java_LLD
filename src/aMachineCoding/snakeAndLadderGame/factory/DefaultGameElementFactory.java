package aMachineCoding.snakeAndLadderGame.factory;


import aMachineCoding.snakeAndLadderGame.element.GameElement;
import aMachineCoding.snakeAndLadderGame.element.Ladder;
import aMachineCoding.snakeAndLadderGame.element.Snake;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameElementFactory implements GameElementFactory {
    public List<GameElement> createGameElements() {
        List<GameElement> elements = new ArrayList<>();
        // Snakes
        elements.add(new Snake(14, 7));
        elements.add(new Snake(31, 26));
        elements.add(new Snake(47, 15));
        elements.add(new Snake(62, 19));
        elements.add(new Snake(99, 78));

        // Ladders
        elements.add(new Ladder(3, 22));
        elements.add(new Ladder(8, 30));
        elements.add(new Ladder(28, 84));
        elements.add(new Ladder(36, 57));
        elements.add(new Ladder(50, 67));

        return elements;
    }
}
