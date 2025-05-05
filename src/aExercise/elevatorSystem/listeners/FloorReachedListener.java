package aExercise.elevatorSystem.listeners;

import aExercise.elevatorSystem.enums.FloorNumber;
import aExercise.elevatorSystem.models.Elevator;

public interface FloorReachedListener {
    void onFloorReached(Elevator elevator, FloorNumber floor);
}