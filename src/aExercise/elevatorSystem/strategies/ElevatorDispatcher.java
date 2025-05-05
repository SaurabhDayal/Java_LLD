package aExercise.elevatorSystem.strategies;

import aExercise.elevatorSystem.models.Elevator;
import aExercise.elevatorSystem.models.Request;

import java.util.List;

public interface ElevatorDispatcher {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}
