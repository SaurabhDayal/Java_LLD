package aMachineCoding.snakeAndFoodGame.models;

import aMachineCoding.snakeAndFoodGame.factories.FoodFactory;
import aMachineCoding.snakeAndFoodGame.factories.FoodItem;
import aMachineCoding.snakeAndFoodGame.strategies.HumanMovementStrategy;
import aMachineCoding.snakeAndFoodGame.strategies.MovementStrategy;

import java.util.Deque;

public class SnakeGame {

    private final Board board;           // Game board (singleton)
    private final Snake snake;           // Snake object
    private final int[][] foodPositions; // List of food coordinates
    private int foodIndex;               // Index of next food to appear
    private MovementStrategy movementStrategy; // Strategy (Human/AI)
    private int score;                   // Player’s score

    // Initialize the game with specified dimensions and food positions
    public SnakeGame(int width, int height, int[][] food) {
        board = Board.getInstance(width, height);
        foodPositions = food;
        foodIndex = 0;
        score = 0;

        // Initialize snake
        snake = new Snake();

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
        Pair currentHead = snake.getBody().peekFirst();

        // Get new head position using chosen strategy
        Pair newHead = movementStrategy.getNextPosition(currentHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        // Check if new head crosses board boundaries
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= board.getHeight()
                || newHeadCol < 0 || newHeadCol >= board.getWidth();

        // Current tail (used to allow safe movement into previous tail position)
        Pair currentTail = snake.getBody().peekLast();

        // Check if snake collides with itself (excluding tail, since it will move)
        boolean bitesItself = snake.getPositionMap().containsKey(newHead) &&
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
            FoodItem food = FoodFactory.createFood(
                    foodPositions[foodIndex],
                    (foodIndex % 3 == 0) ? "bonus" : "normal"
            );
            score += food.getPoints();  // Add points to score
            foodIndex++;                // Move to next food
        } else {
            // No food eaten → remove tail (snake moves forward)
            snake.getBody().pollLast();
            snake.getPositionMap().remove(currentTail);
        }

        // Add new head to snake body
        snake.getBody().addFirst(newHead);
        snake.getPositionMap().put(newHead, true);

        // Return current score
        return score;
    }

    // Getter for snake body (used by rendering/printing)
    public Deque<Pair> getSnakeBody() {
        return snake.getBody();
    }

    // Getter for snake object (if needed directly)
    public Snake getSnake() {
        return snake;
    }
}
