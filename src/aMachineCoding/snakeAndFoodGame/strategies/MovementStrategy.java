package aMachineCoding.snakeAndFoodGame.strategies;


import aMachineCoding.snakeAndFoodGame.models.Pair;

public interface MovementStrategy {
    String getLastDirection();

    Pair getNextPosition(Pair currentHead, String direction);
}