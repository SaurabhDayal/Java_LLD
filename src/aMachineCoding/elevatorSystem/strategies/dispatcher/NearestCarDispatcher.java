package aMachineCoding.elevatorSystem.strategies.dispatcher;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.HallRequest;
import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.ElevatorStatus;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

import java.util.List;

public class NearestCarDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, HallRequest hallRequest) {

        Elevator selected = null;
        int minDistance = Integer.MAX_VALUE;
        int reqFloor = hallRequest.floor().getFloorValue();

        for (Elevator e : elevators) {

            if (e.getStatus() == ElevatorStatus.IDLE) {
                // prefer idle elevator immediately if it's very close
                int dist = Math.abs(e.getCurrentFloor().getFloorValue() - reqFloor);
                if (dist < minDistance) {
                    minDistance = dist;
                    selected = e;
                }
                continue;
            }

            // If moving in same direction and request is along the way, prefer it
            Direction dir = e.getDirection();
            int cur = e.getCurrentFloor().getFloorValue();
            boolean useful = false;

            if (dir == Direction.UP && cur <= reqFloor && hallRequest.direction() == Direction.UP) {
                useful = true;
            }
            if (dir == Direction.DOWN && cur >= reqFloor && hallRequest.direction() == Direction.DOWN) {
                useful = true;
            }

            int dist = Math.abs(cur - reqFloor);
            if (useful) {
                // prefer direction-aligned elevators
                dist = dist - 1; // slight bias
            }

            if (dist < minDistance) {
                minDistance = dist;
                selected = e;
            }
        }

        // fallback
        if (selected == null && !elevators.isEmpty()) {
            selected = elevators.getFirst();
        }
        return selected;
    }
}
