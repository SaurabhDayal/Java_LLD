package aMachineCoding.snakeAndFoodGame.factories;

import aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems.BonusFood;
import aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems.NormalFood;
import aMachineCoding.snakeAndFoodGame.models.Pair;

public class FoodFactory {
    // Create a food item at a given position (bonus or normal)
    public static FoodItem createFood(Pair position, String type) {
        if ("bonus".equals(type)) {
            return new BonusFood(position.getRow(), position.getCol()); // Create bonus food
        }
        return new NormalFood(position.getRow(), position.getCol());    // Default to normal food
    }
}
