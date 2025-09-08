package aMachineCoding.snakeAndFoodGame.models;

import aMachineCoding.snakeAndFoodGame.factories.FoodFactory;
import aMachineCoding.snakeAndFoodGame.factories.FoodItem;
import aMachineCoding.snakeAndFoodGame.strategies.HumanMovementStrategy;
import aMachineCoding.snakeAndFoodGame.strategies.MovementStrategy;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SnakeGame {

    private final Board board;                 // Game board (singleton)
    public Deque<Pair> snake;                  // Snake body represented as a deque
    private final Map<Pair, Boolean> snakeMap; // Quick lookup for collision detection
    private final int[][] foodPositions;       // List of food coordinates
    private int foodIndex;                     // Index of next food to appear
    private MovementStrategy movementStrategy; // Strategy (Human/AI)
    private int score;                         // Player’s score

    // Initialize the game with specified dimensions and food positions
    public SnakeGame(int width, int height, int[][] food) {
        board = Board.getInstance(width, height);
        foodPositions = food;
        foodIndex = 0;
        score = 0;

        // Start snake at position (0,0)
        snake = new LinkedList<>();
        snakeMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        snake.offerFirst(initialPos);
        snakeMap.put(initialPos, true);

        // Default movement strategy is human-controlled
        movementStrategy = new HumanMovementStrategy();
    }

    // Allow switching between human or AI movement
    public void setMovementStrategy(MovementStrategy strategy) {
        movementStrategy = strategy;
    }

    // Process one move; returns updated score or -1 if game over
    public int move(String direction) {
        // Current snake head
        Pair currentHead = snake.peekFirst();

        // Get new head position using chosen strategy
        Pair newHead = movementStrategy.getNextPosition(currentHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        // Check if new head crosses board boundaries
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= board.getHeight()
                || newHeadCol < 0 || newHeadCol >= board.getWidth();

        // Current tail (used to allow safe movement into previous tail position)
        Pair currentTail = snake.peekLast();

        // Check if snake collides with itself (excluding tail, since it will move)
        boolean bitesItself = snakeMap.containsKey(newHead) &&
                !(newHead.getRow() == currentTail.getRow() &&
                        newHead.getCol() == currentTail.getCol());

        // Game over if boundary crossed or snake bites itself
        if (crossesBoundary || bitesItself) {
            return -1;
        }

        // Check if snake eats food at new head position
        boolean ateFood = (foodIndex < foodPositions.length) &&
                (foodPositions[foodIndex][0] == newHeadRow) &&
                (foodPositions[foodIndex][1] == newHeadCol);

        if (ateFood) {
            // Create food object (bonus for every 3rd food)
            FoodItem food = FoodFactory.createFood(foodPositions[foodIndex], (foodIndex % 3 == 0) ? "bonus" : "normal");
            score += food.getPoints();  // Add points to score
            foodIndex++;                // Move to next food
        } else {
            // No food eaten → remove tail (snake moves forward)
            snake.pollLast();
            snakeMap.remove(currentTail);
        }

        // Add new head to snake body
        snake.addFirst(newHead);
        snakeMap.put(newHead, true);

        // Return current score
        return score;
    }

    // Getter for snake body (used by rendering/printing)
    public Deque<Pair> getSnake() {
        return snake;
    }
}
