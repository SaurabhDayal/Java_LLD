package aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.models;

import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.element.GameElement;

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
