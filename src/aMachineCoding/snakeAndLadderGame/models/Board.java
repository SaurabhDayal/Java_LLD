package aMachineCoding.snakeAndLadderGame.models;

import aMachineCoding.snakeAndLadderGame.element.GameElement;
import aMachineCoding.snakeAndLadderGame.factory.GameElementFactory;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size = 100;
    private List<Cell> cells;
    private List<GameElement> gameElements;

    public Board(GameElementFactory factory) {
        this.cells = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            cells.add(new Cell(i));
        }

        this.gameElements = factory.createGameElements();
        for (GameElement element : gameElements) {
            int index = element.getStartPosition() - 1;
            cells.get(index).setGameElement(element);
        }
    }

    public Cell getCell(int position) {
        return cells.get(position - 1);
    }

    public int getSize() {
        return size;
    }
}
