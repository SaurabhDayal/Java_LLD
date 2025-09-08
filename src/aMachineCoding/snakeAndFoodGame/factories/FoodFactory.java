package aMachineCoding.snakeAndFoodGame.factories;

import aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems.BonusFood;
import aMachineCoding.snakeAndFoodGame.factories.ConcreteFoodItems.NormalFood;

public class FoodFactory {
    public static FoodItem createFood(int[] position, String type) {
        if ("bonus".equals(type)) {
            return new BonusFood(position[0], position[1]); // Create bonus food
        }
        return new NormalFood(position[0], position[1]);    // Default to normal food
    }
}
