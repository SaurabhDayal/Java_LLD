package aMachineCoding.elevatorSystem.strategies.dispatcher;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.HallRequest;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

import java.util.List;

public class LoadBalancingDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, HallRequest hallRequest) {
        Elevator best = null;
        int minPending = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int pending = e.getPendingRequestCount(); // Todo: get actual pending request
            if (pending < minPending) {
                minPending = pending;
                best = e;
            }
        }
        if (best == null && !elevators.isEmpty()) {
            best = elevators.get(0);
        }
        return best;
    }
}
