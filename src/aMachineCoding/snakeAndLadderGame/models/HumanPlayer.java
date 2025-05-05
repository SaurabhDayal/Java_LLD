package aMachineCoding.snakeAndLadderGame.models;

import aMachineCoding.snakeAndLadderGame.dice.DiceStrategy;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void takeTurn(Board board, DiceStrategy dice) {
        int steps = dice.roll();
        int target = position + steps;
        if (target > board.getSize()) return;
        Cell cell = board.getCell(target);
        this.position = cell.getNextPosition();
    }
}
