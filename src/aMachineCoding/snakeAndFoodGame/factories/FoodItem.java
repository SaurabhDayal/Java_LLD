package aMachineCoding.snakeAndFoodGame.factories;


public abstract class FoodItem {

    protected int row, column; // Position of the food item
    protected int points;      // Points awarded when consumed
    
    public FoodItem(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPoints() {
        return points;
    }
}
