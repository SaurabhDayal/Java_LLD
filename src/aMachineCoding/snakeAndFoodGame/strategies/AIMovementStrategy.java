package aMachineCoding.snakeAndFoodGame.strategies;

import aMachineCoding.snakeAndFoodGame.models.Pair;

import java.util.Random;

public class AIMovementStrategy implements MovementStrategy {
    private final Random random = new Random();
    private String lastDirection = "R"; // Default initial movement (right)

    @Override
    public String getLastDirection() {
        return lastDirection;
    }

    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        // Possible directions
        String[] dirs = {"U", "D", "L", "R"};
        String move;

        // Keep picking until we get a non-opposite move
        do {
            move = dirs[random.nextInt(4)];
        } while (isOpposite(move, lastDirection));

        // Update last direction
        lastDirection = move;

        int row = currentHead.getRow();
        int col = currentHead.getCol();

        // Return new head position based on chosen move
        return switch (move) {
            case "U" -> new Pair(row - 1, col);
            case "D" -> new Pair(row + 1, col);
            case "L" -> new Pair(row, col - 1);
            case "R" -> new Pair(row, col + 1);
            default -> currentHead; // fallback (shouldn’t occur)
        };
    }

    // Helper to check if two directions are opposites
    private boolean isOpposite(String dir1, String dir2) {
        return (dir1.equals("U") && dir2.equals("D")) ||
                (dir1.equals("D") && dir2.equals("U")) ||
                (dir1.equals("L") && dir2.equals("R")) ||
                (dir1.equals("R") && dir2.equals("L"));
    }
}
