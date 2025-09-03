package aMachineCoding.elevatorSystem.strategies.dispatcher;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.HallRequest;
import aMachineCoding.elevatorSystem.models.enums.ElevatorStatus;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

import java.util.List;

public class IdleFirstDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, HallRequest hallRequest) {
        for (Elevator e : elevators) {
            if (e.getStatus() == ElevatorStatus.IDLE) {
                return e;
            }
        }
        // fallback: choose elevator with least pending
        Elevator best = null;
        int min = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int pending = e.getPendingRequestCount(); // Todo: get actual pending request
            if (pending < min) {
                min = pending;
                best = e;
            }
        }
        return best;
    }
}
