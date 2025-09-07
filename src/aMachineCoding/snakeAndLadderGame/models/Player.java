package aMachineCoding.snakeAndLadderGame.models;

import aMachineCoding.snakeAndLadderGame.dice.DiceStrategy;

public abstract class Player {

    protected String name;
    protected int position;

    public Player(String name) {
        this.name = name;
        this.position = 1;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public abstract void takeTurn(Board board, DiceStrategy dice);
}

