package aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems;

import aMachineCoding.snakeAndFoodGame.factories.FoodItem;


public class NormalFood extends FoodItem {
    public NormalFood(int row, int column) {
        super(row, column); // Call superclass constructor
        this.points = 1;    // Assign point value
    }
}