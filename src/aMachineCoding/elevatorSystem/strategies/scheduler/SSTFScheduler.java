package aMachineCoding.elevatorSystem.strategies.scheduler;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SSTFScheduler implements ElevatorScheduler {

    private final Map<ElevatorID, Set<FloorNumber>> map = new HashMap<>();

    @Override
    public synchronized void addRequest(Elevator elevator, FloorNumber floor) {
        map.computeIfAbsent(elevator.getId(), k -> new HashSet<>());
        map.get(elevator.getId()).add(floor);
    }

    @Override
    public synchronized FloorNumber peekNext(Elevator elevator) {
        Set<FloorNumber> set = map.get(elevator.getId());
        if (set == null || set.isEmpty()) return null;
        int cur = elevator.getCurrentFloor().getFloorValue();
        FloorNumber best = null;
        int min = Integer.MAX_VALUE;
        for (FloorNumber f : set) {
            int d = Math.abs(f.getFloorValue() - cur);
            if (d < min) {
                min = d;
                best = f;
            }
        }
        return best;
    }

    @Override
    public synchronized void removeRequest(Elevator elevator, FloorNumber floor) {
        Set<FloorNumber> set = map.get(elevator.getId());
        if (set != null) set.remove(floor);
    }

    @Override
    public synchronized boolean hasPendingRequests(Elevator elevator) {
        Set<FloorNumber> set = map.get(elevator.getId());
        return set != null && !set.isEmpty();
    }
}
