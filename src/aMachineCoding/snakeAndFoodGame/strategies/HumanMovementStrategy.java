package aMachineCoding.snakeAndFoodGame.strategies;

import aMachineCoding.snakeAndFoodGame.models.Pair;

public class HumanMovementStrategy implements MovementStrategy {
    private String lastDirection = "R"; // default initial movement (right)

    @Override
    public String getLastDirection() {
        return lastDirection;
    }

    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        // Prevent opposite moves
        if (isOpposite(direction, lastDirection)) {
            direction = lastDirection; // ignore invalid move, keep moving in last direction
        }
        lastDirection = direction;

        int row = currentHead.getRow();
        int col = currentHead.getCol();
        return switch (direction) {
            case "U" -> new Pair(row - 1, col);
            case "D" -> new Pair(row + 1, col);
            case "L" -> new Pair(row, col - 1);
            case "R" -> new Pair(row, col + 1);
            default -> currentHead;
        };
    }

    private boolean isOpposite(String dir1, String dir2) {
        return (dir1.equals("U") && dir2.equals("D")) ||
                (dir1.equals("D") && dir2.equals("U")) ||
                (dir1.equals("L") && dir2.equals("R")) ||
                (dir1.equals("R") && dir2.equals("L"));
    }
}
