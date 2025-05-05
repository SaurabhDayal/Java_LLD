package aExercise.elevatorSystem.strategies;

import aExercise.elevatorSystem.enums.ElevatorStatus;
import aExercise.elevatorSystem.models.Elevator;
import aExercise.elevatorSystem.models.Request;

import java.util.List;

public class FCFSDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        // Select the first idle elevatorSystem available
        for (Elevator e : elevators) {
            if (e.getStatus() == ElevatorStatus.IDLE) {
                return e;
            }
        }

        // Fallback: if no elevatorSystem is idle, return the first elevatorSystem (simplified handling)
        return elevators.isEmpty() ? null : elevators.get(0);
    }
}
