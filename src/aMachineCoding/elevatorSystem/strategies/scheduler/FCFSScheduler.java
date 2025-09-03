package aMachineCoding.elevatorSystem.strategies.scheduler;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class FCFSScheduler implements ElevatorScheduler {

    private final Map<ElevatorID, Queue<FloorNumber>> map = new HashMap<>();

    @Override
    public synchronized void addRequest(Elevator elevator, FloorNumber floor) {
        map.computeIfAbsent(elevator.getId(), k -> new LinkedList<>());
        Queue<FloorNumber> q = map.get(elevator.getId());
        if (!q.contains(floor)) q.add(floor);
    }

    @Override
    public synchronized FloorNumber peekNext(Elevator elevator) {
        Queue<FloorNumber> q = map.get(elevator.getId());
        if (q == null || q.isEmpty()) return null;
        return q.peek();
    }

    @Override
    public synchronized void removeRequest(Elevator elevator, FloorNumber floor) {
        Queue<FloorNumber> q = map.get(elevator.getId());
        if (q != null) q.remove(floor);
    }

    @Override
    public synchronized boolean hasPendingRequests(Elevator elevator) {
        Queue<FloorNumber> q = map.get(elevator.getId());
        return q != null && !q.isEmpty();
    }
}
