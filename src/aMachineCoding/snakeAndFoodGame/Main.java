package aMachineCoding.snakeAndFoodGame;

import aMachineCoding.snakeAndFoodGame.models.SnakeGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Define game configuration can be taken as user input as well
        int width = 20;
        int height = 15;
        // Define some food positions (more can be generated during gameplay)
        int[][] foodPositions = {
                {0, 1},   // First food
                {2, 4},   // Second food
                {10, 8},  // Third food
                {3, 12},  // Fourth food
                {8, 17},  // Fifth food
                {12, 3}   // Sixth food
        };

        SnakeGame game = new SnakeGame(width, height, foodPositions);

        System.out.println("===== SNAKE GAME =====");
        System.out.println("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
        System.out.println("Eat food to grow your snake and increase your score.");
        System.out.println("Don't hit the walls or bite yourself!");
        System.out.println("=======================");

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        int score = 0;

        // Main game loop
        while (gameRunning) {

            displayGameState(game);

            // Get user input
            System.out.print("Enter move (W/A/S/D) or Q to quit: ");
            String input = scanner.nextLine().toUpperCase();
            // Handle quit command
            if (input.equals("Q")) {
                System.out.println("Game ended by player. Final score: " + score);
                gameRunning = false;
                continue;
            }
            String direction = convertInput(input);   // Convert WASD input to UDLR for game processing
            // Skip invalid inputs
            if (direction.isEmpty()) {
                direction = game.getMovementStrategy().getLastDirection();
            }

            // Make the move and get the new score
            score = game.move(direction);

            // Check for game over
            if (score == -1) {
                System.out.println("GAME OVER! You hit a wall or bit yourself.");
                System.out.println("Final score: " + (game.getSnake().size() - 1));
                gameRunning = false;
            } else {
                System.out.println("Score: " + score);
            }
        }
        scanner.close();
        System.out.println("Thanks for playing!");
    }

    // Convert user-friendly WASD input to UDLR for the game engine
    private static String convertInput(String input) {
        return switch (input) {
            case "W" -> "U"; // Up
            case "S" -> "D"; // Down
            case "A" -> "L"; // Left
            case "D" -> "R"; // Right
            default -> "";   // Invalid input
        };
    }

    // A simple method to display the game state in the console
    // In a real implementation, this would be replaced with graphics
    private static void displayGameState(SnakeGame game) {
        // In a complete implementation, you would render the board with the
        // snake, food, and boundaries visually
        System.out.println("Snake length   : " + game.getSnake().size());
        System.out.println("Snake position : " + game.getSnake().getBody().peekFirst());
    }
}