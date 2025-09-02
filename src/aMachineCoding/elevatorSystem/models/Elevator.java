package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.models.panels.InsidePanel;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator implements Runnable {

    private final ElevatorID id;                  // Unique ID of this elevator
    private FloorNumber currentFloor;             // Current floor of the elevator
    private Direction direction;                  // Current moving direction
    private ElevatorStatus status;                // Current status (IDLE, MOVING, etc.)
    private final Queue<FloorNumber> requests;    // Queue of pending floor requests
    private volatile boolean keepRunning;         // Flag to control elevator thread
    private FloorReachedListener floorReachedListener; // Listener for floor reached events
    private final InsidePanel insidePanel;        // Panel inside elevator with floor buttons

    public Elevator(ElevatorID id) {
        this.id = id;
        this.currentFloor = FloorNumber.F_0;      // Start at ground floor
        this.direction = Direction.UP;            // Default initial direction
        this.status = ElevatorStatus.IDLE;        // Initially idle
        this.requests = new LinkedList<>();       // Initialize request queue
        this.keepRunning = true;                  // Thread should run
        this.insidePanel = new InsidePanel(this); // Attach inside panel
    }

    public InsidePanel getInsidePanel() {
        return insidePanel; // Return the attached inside panel
    }

    public void setFloorReachedListener(FloorReachedListener listener) {
        this.floorReachedListener = listener; // Register floor reached listener
    }

    public void setDirection(Direction direction) {
        this.direction = direction; // Set elevator direction manually
    }

    public ElevatorID getId() {
        return id; // Return elevator ID
    }

    public FloorNumber getCurrentFloor() {
        return currentFloor; // Return current floor
    }

    public Direction getDirection() {
        return direction; // Return current direction
    }

    public ElevatorStatus getStatus() {
        return status; // Return current status
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status; // Update elevator status
    }

    public void stopElevator() {
        this.keepRunning = false; // Stop the elevator thread
    }

    public void addRequest(FloorNumber floor) {
        synchronized (requests) {
            if (!requests.contains(floor)) { // Avoid duplicate requests
                requests.add(floor);         // Add new floor request
                status = ElevatorStatus.MOVING; // Elevator is now moving

                // Determine direction based on target floor
                if (floor.getFloorValue() > currentFloor.getFloorValue()) {
                    direction = Direction.UP;
                } else if (floor.getFloorValue() < currentFloor.getFloorValue()) {
                    direction = Direction.DOWN;
                }
            }
        }
    }

    public Queue<FloorNumber> getRequests() {
        return requests; // Return all pending requests
    }

    public boolean hasPendingRequestAbove(FloorNumber floor) {
        synchronized (requests) {
            for (FloorNumber req : requests) {
                if (req.getFloorValue() > floor.getFloorValue()) return true; // Pending request above
            }
            return false; // No pending request above
        }
    }

    public boolean hasPendingRequestBelow(FloorNumber floor) {
        synchronized (requests) {
            for (FloorNumber req : requests) {
                if (req.getFloorValue() < floor.getFloorValue()) return true; // Pending request below
            }
            return false; // No pending request below
        }
    }

    @Override
    public void run() {
        while (keepRunning) { // Elevator main loop
            move(); // Move elevator one step
            try {
                Thread.sleep(1000); // Simulate time between floors
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
    }

    private void move() {
        synchronized (requests) {
            if (requests.isEmpty()) { // No requests, stay idle
                status = ElevatorStatus.IDLE;
                return;
            }

            FloorNumber targetFloor = requests.peek(); // Next floor in queue

            // Move one floor up or down
            if (currentFloor.getFloorValue() < targetFloor.getFloorValue()) {
                currentFloor = FloorNumber.values()[currentFloor.getFloorValue() + 1];
                direction = Direction.UP;
            } else if (currentFloor.getFloorValue() > targetFloor.getFloorValue()) {
                currentFloor = FloorNumber.values()[currentFloor.getFloorValue() - 1];
                direction = Direction.DOWN;
            }

            System.out.printf("    - Elevator %s at floor %s, moving towards %s%n", id, currentFloor, targetFloor);

            if (currentFloor == targetFloor) { // Reached target floor
                System.out.printf("    - Elevator %s reached target floor %s%n", id, currentFloor);
                requests.poll(); // Remove fulfilled request

                if (floorReachedListener != null) { // Notify listener
                    floorReachedListener.onFloorReached(this, currentFloor);
                }

                // Reset inside panel button for this floor
                insidePanel.getElevatorButtonList().forEach(button -> {
                    if (button.getFloorNumber().getFloorValue() == currentFloor.getFloorValue()) {
                        button.reset();
                    }
                });

                // Update status and direction for next request
                if (requests.isEmpty()) {
                    status = ElevatorStatus.IDLE; // No more requests
                } else {
                    FloorNumber next = requests.peek();
                    if (next.getFloorValue() > currentFloor.getFloorValue()) direction = Direction.UP;
                    else if (next.getFloorValue() < currentFloor.getFloorValue()) direction = Direction.DOWN;
                }
            }
        }
    }
}
