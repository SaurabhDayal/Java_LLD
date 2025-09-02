package aMachineCoding.elevatorSystem.strategies;

import aMachineCoding.elevatorSystem.models.Direction;
import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.ElevatorStatus;
import aMachineCoding.elevatorSystem.models.Request;

import java.util.List;

public class LOOKDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        int requestedFloor = request.getFloor().getFloorValue();
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getStatus() == ElevatorStatus.IDLE || e.getStatus() == ElevatorStatus.MOVING) {
                boolean isEligible = false;

                int currentFloor = e.getCurrentFloor().getFloorValue();

                if (e.getDirection() == Direction.UP) {
                    if (currentFloor <= requestedFloor && e.hasPendingRequestAbove(request.getFloor())) {
                        isEligible = true;
                    }
                } else if (e.getDirection() == Direction.DOWN) {
                    if (currentFloor >= requestedFloor && e.hasPendingRequestBelow(request.getFloor())) {
                        isEligible = true;
                    }
                }

                if (isEligible) {
                    int distance = Math.abs(currentFloor - requestedFloor);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = e;
                    }
                }
            }
        }

        if (bestElevator == null && !elevators.isEmpty()) {
            bestElevator = elevators.get(0); // fallback
        }

        return bestElevator;
    }
}
