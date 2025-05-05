package aExercise.snakeAndLadderGame.dice;

public class StandardDice implements DiceStrategy {
    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}
