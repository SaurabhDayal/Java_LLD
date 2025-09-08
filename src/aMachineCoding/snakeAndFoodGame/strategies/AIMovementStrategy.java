package aMachineCoding.snakeAndFoodGame.strategies;

import aMachineCoding.snakeAndFoodGame.models.Pair;

import java.util.Random;

public class AIMovementStrategy implements MovementStrategy {
    private final Random random = new Random();

    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        // Randomly pick a direction if AI-controlled
        String[] dirs = {"U", "D", "L", "R"};
        String move = dirs[random.nextInt(4)];

        int row = currentHead.getRow();
        int col = currentHead.getCol();
        return switch (move) {
            case "U" -> new Pair(row - 1, col);
            case "D" -> new Pair(row + 1, col);
            case "L" -> new Pair(row, col - 1);
            case "R" -> new Pair(row, col + 1);
            default -> currentHead;
        };
    }
}
