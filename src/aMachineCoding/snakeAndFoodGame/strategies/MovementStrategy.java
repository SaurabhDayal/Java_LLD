package aMachineCoding.snakeAndFoodGame.strategies;


import aMachineCoding.snakeAndFoodGame.models.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}