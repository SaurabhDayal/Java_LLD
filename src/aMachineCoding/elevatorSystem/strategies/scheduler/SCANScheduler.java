package aMachineCoding.elevatorSystem.strategies.scheduler;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class SCANScheduler implements ElevatorScheduler {

    private static class PerElevator {
        final TreeSet<Integer> up = new TreeSet<>();   // Floors above current position
        final TreeSet<Integer> down = new TreeSet<>(); // Floors below current position
    }

    private final Map<ElevatorID, PerElevator> map = new ConcurrentHashMap<>();

    @Override
    public synchronized void addRequest(Elevator elevator, FloorNumber floor) {
        PerElevator p = map.computeIfAbsent(elevator.getId(), k -> new PerElevator());

        int f = floor.getFloorValue();
        if (f > elevator.getCurrentFloor().getFloorValue()) {
            p.up.add(f);
        } else if (f < elevator.getCurrentFloor().getFloorValue()) {
            p.down.add(f);
        } else {
            p.up.add(f); // same floor → add to UP by convention
        }
    }

    @Override
    public synchronized FloorNumber peekNext(Elevator elevator) {
        PerElevator p = map.get(elevator.getId());
        if (p == null) {
            return null;
        }

        Direction dir = elevator.getDirection();
        int cur = elevator.getCurrentFloor().getFloorValue();

        if (dir == Direction.UP) {
            Integer nextUp = p.up.ceiling(cur);
            if (nextUp != null) {
                return FloorNumber.values()[nextUp];
            }

            // SCAN: no more UP requests → still go to top floor (max boundary) then reverse
            int topFloor = FloorNumber.values()[FloorNumber.values().length - 1].getFloorValue();
            if (cur < topFloor) {
                return FloorNumber.values()[topFloor];
            }

            // At top floor → now handle DOWN
            Integer nextDown = p.down.isEmpty() ? null : p.down.last();
            if (nextDown != null) {
                return FloorNumber.values()[nextDown];
            }

        } else if (dir == Direction.DOWN) {
            Integer nextDown = p.down.floor(cur);
            if (nextDown != null) {
                return FloorNumber.values()[nextDown];
            }

            // SCAN: no more DOWN requests → still go to bottom floor (0) then reverse
            int bottomFloor = FloorNumber.values()[0].getFloorValue();
            if (cur > bottomFloor) {
                return FloorNumber.values()[bottomFloor];
            }

            // At bottom floor → now handle UP
            Integer nextUp = p.up.isEmpty() ? null : p.up.first();
            if (nextUp != null) {
                return FloorNumber.values()[nextUp];
            }

        } else {
            // IDLE → prefer UP first, else DOWN
            if (!p.up.isEmpty()) {
                return FloorNumber.values()[p.up.first()];
            }
            if (!p.down.isEmpty()) {
                return FloorNumber.values()[p.down.last()];
            }
        }

        return null;
    }

    @Override
    public synchronized void removeRequest(Elevator elevator, FloorNumber floor) {
        PerElevator p = map.get(elevator.getId());
        if (p == null) return;

        int f = floor.getFloorValue();
        p.up.remove(f);
        p.down.remove(f);
    }

    @Override
    public synchronized boolean hasPendingRequests(Elevator elevator) {
        PerElevator p = map.get(elevator.getId());
        return p != null && (!p.up.isEmpty() || !p.down.isEmpty());
    }
}
