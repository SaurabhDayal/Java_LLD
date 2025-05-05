package aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.models;

import aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.dice.DiceStrategy;

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
