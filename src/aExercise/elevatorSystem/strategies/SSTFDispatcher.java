package aExercise.elevatorSystem.strategies;

import aExercise.elevatorSystem.enums.ElevatorStatus;
import aExercise.elevatorSystem.models.Elevator;
import aExercise.elevatorSystem.models.Request;

import java.util.List;

public class SSTFDispatcher implements ElevatorDispatcher {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator selectedElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            // Skip elevators that are out of service or unavailable
            if (elevator.getStatus() != ElevatorStatus.IDLE && elevator.getStatus() != ElevatorStatus.MOVING) {
                continue;
            }

            // Calculate distance from current elevatorSystem position to requested floor
            int distance = Math.abs(elevator.getCurrentFloor().getFloorValue() - request.getFloor().getFloorValue());

            // If this elevatorSystem is closer, choose it
            if (distance < minDistance) {
                minDistance = distance;
                selectedElevator = elevator;
            }
        }

        // Return the selected elevatorSystem or null if none are suitable
        return selectedElevator;
    }
}
