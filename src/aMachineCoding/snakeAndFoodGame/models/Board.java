package aMachineCoding.snakeAndFoodGame.models;


public class Board {

    private static Board instance; // Single instance of the game board
    private final int width;       // Width of the game board
    private final int height;      // Height of the game board
    
    private Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Board getInstance(int width, int height) {
        if (instance == null) {
            instance = new Board(width, height); // Create instance if not already created
        }
        return instance; // Return the existing instance
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
