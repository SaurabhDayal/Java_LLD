package aMachineCoding.elevatorSystem.strategies.scheduler;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class LOOKScheduler implements ElevatorScheduler {

    private static class PerElevator {
        final TreeSet<Integer> up = new TreeSet<>();   // Floors above current position
        final TreeSet<Integer> down = new TreeSet<>(); // Floors below current position
    }

    private final Map<ElevatorID, PerElevator> map = new ConcurrentHashMap<>();

    @Override
    public synchronized void addRequest(Elevator elevator, FloorNumber floor) {
        // Fetch per-elevator queues, create if absent
        PerElevator p = map.computeIfAbsent(elevator.getId(), k -> new PerElevator());

        int f = floor.getFloorValue();
        // Classify request based on current elevator floor
        if (f > elevator.getCurrentFloor().getFloorValue()) {
            p.up.add(f);   // future UP movement
        } else if (f < elevator.getCurrentFloor().getFloorValue()) {
            p.down.add(f); // future DOWN movement
        } else {
            // same floor → add to UP queue by convention
            p.up.add(f);
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
            // Find next request above or at current floor
            Integer nextUp = p.up.ceiling(cur);
            if (nextUp != null) return FloorNumber.values()[nextUp];

            // No UP requests → immediately reverse to DOWN
            Integer nextDown = p.down.isEmpty() ? null : p.down.last();
            if (nextDown != null) return FloorNumber.values()[nextDown];
        } else if (dir == Direction.DOWN) {
            // Find next request below or at current floor
            Integer nextDown = p.down.floor(cur);
            if (nextDown != null) return FloorNumber.values()[nextDown];

            // No DOWN requests → immediately reverse to UP
            Integer nextUp = p.up.isEmpty() ? null : p.up.first();
            if (nextUp != null) return FloorNumber.values()[nextUp];
        } else {
            // IDLE → prefer UP first, else DOWN
            if (!p.up.isEmpty()) return FloorNumber.values()[p.up.first()];
            if (!p.down.isEmpty()) return FloorNumber.values()[p.down.last()];
        }
        return null;
    }

    @Override
    public synchronized void removeRequest(Elevator elevator, FloorNumber floor) {
        PerElevator p = map.get(elevator.getId());
        if (p == null) return;
        int f = floor.getFloorValue();
        p.up.remove(f);   // Remove from UP if present
        p.down.remove(f); // Remove from DOWN if present
    }

    @Override
    public synchronized boolean hasPendingRequests(Elevator elevator) {
        PerElevator p = map.get(elevator.getId());
        return p != null && (!p.up.isEmpty() || !p.down.isEmpty());
    }
}
