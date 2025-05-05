package aScalerModule_08_LLD_3.assign_02.snakeAndLadderGame.dice;

public class StandardDice implements DiceStrategy {
    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}
