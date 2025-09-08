package aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems;

import aMachineCoding.snakeAndFoodGame.factories.FoodItem;

public class BonusFood extends FoodItem {
    public BonusFood(int row, int column) {
        super(row, column); // Call superclass constructor
        this.points = 3;    // Assign higher point value
    }
}
