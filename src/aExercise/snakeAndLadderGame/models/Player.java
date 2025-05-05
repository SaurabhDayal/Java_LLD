package aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.models;

import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.dice.DiceStrategy;

public abstract class Player {
    protected String name;
    protected int position = 1;

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Player(String name) {
        this.name = name;
    }

    public abstract void takeTurn(Board board, DiceStrategy dice);
}

