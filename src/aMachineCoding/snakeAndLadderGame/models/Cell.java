package aMachineCoding.snakeAndLadderGame.models;

import aMachineCoding.snakeAndLadderGame.element.GameElement;

class Cell {

    private final int position;
    private GameElement gameElement;

    public Cell(int position) {
        this.position = position;
    }

    public void setGameElement(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    public int getNextPosition() {
        if (gameElement != null) {
            return gameElement.getDestination();
        }
        return position;
    }
}
