package aExercise.elevatorSystem.strategies;

import aExercise.elevatorSystem.enums.Direction;
import aExercise.elevatorSystem.enums.ElevatorStatus;
import aExercise.elevatorSystem.models.Elevator;
import aExercise.elevatorSystem.models.Request;

import java.util.List;

public class SCANDispatcher implements ElevatorDispatcher {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator selectedElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getStatus() == ElevatorStatus.IDLE || e.getStatus() == ElevatorStatus.MOVING) {
                boolean isValid = false;

                int elevatorFloor = e.getCurrentFloor().getFloorValue();
                int requestFloor = request.getFloor().getFloorValue();

                if (e.getDirection() == Direction.UP && elevatorFloor <= requestFloor) {
                    isValid = true;
                } else if (e.getDirection() == Direction.DOWN && elevatorFloor >= requestFloor) {
                    isValid = true;
                }

                if (isValid) {
                    int distance = Math.abs(elevatorFloor - requestFloor);
                    if (distance < minDistance) {
                        minDistance = distance;
                        selectedElevator = e;
                    }
                }
            }
        }

        return selectedElevator != null ? selectedElevator : elevators.get(0);
    }
}
