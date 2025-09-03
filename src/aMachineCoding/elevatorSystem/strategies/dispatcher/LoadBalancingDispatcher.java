package aMachineCoding.elevatorSystem.strategies.dispatcher;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.Request;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

import java.util.List;

public class LoadBalancingDispatcher implements ElevatorDispatcher {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator best = null;
        int minPending = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int pending = e.getPendingRequestCount();
            if (pending < minPending) {
                minPending = pending;
                best = e;
            }
        }
        if (best == null && !elevators.isEmpty()) best = elevators.get(0);
        return best;
    }
}
