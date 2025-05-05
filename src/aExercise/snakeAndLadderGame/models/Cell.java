package aExercise.snakeAndLadderGame.models;

import aExercise.snakeAndLadderGame.element.GameElement;

class Cell {
    private int position;
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
