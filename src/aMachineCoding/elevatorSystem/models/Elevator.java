package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.ElevatorStatus;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.panels.InsidePanel;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

public class Elevator implements Runnable {

    private final ElevatorID id;                  // Unique ID of this elevator
    private FloorNumber currentFloor;             // Current floor of the elevator
    private Direction direction;                  // Current moving direction
    private ElevatorStatus status;                // Current status (IDLE, MOVING, etc.)
    private volatile boolean keepRunning;         // Flag to control elevator thread
    private FloorReachedListener floorReachedListener; // Listener for floor reached events
    private final InsidePanel insidePanel;        // Panel inside elevator with floor buttons
    private final ElevatorScheduler scheduler;    // Scheduler for this elevator

    public Elevator(ElevatorID id, ElevatorScheduler scheduler) {
        this.id = id;
        this.currentFloor = FloorNumber.GROUND;      // Start at ground floor
        this.direction = Direction.UP;            // Default initial direction
        this.status = ElevatorStatus.IDLE;        // Initially idle
        this.keepRunning = true;                  // Thread should run
        this.insidePanel = new InsidePanel(this); // Attach inside panel
        this.scheduler = scheduler;
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
        synchronized (scheduler) {
            if (!scheduler.hasPendingRequests(this) || !isDuplicate(floor)) {
                scheduler.addRequest(this, floor); // delegate to scheduler
                status = ElevatorStatus.MOVING;   // Elevator is now moving
                // Update direction bias based on new target
                FloorNumber target = scheduler.peekNext(this);
                if (target != null) {
                    if (target.getFloorValue() > currentFloor.getFloorValue()) {
                        direction = Direction.UP;
                    } else if (target.getFloorValue() < currentFloor.getFloorValue()) {
                        direction = Direction.DOWN;
                    }
                }
            }
        }
    }

    private boolean isDuplicate(FloorNumber floor) {
        // best-effort duplicate detection: if scheduler already has that request
        // This is scheduler implementation dependent. We'll try peek & scan where possible.
        // For simplicity, call peekNext and check equality or rely on scheduler's internal dedupe.
        FloorNumber p = scheduler.peekNext(this);
        return p != null && p.getFloorValue() == floor.getFloorValue();
    }

    public int getPendingRequestCount() {
        return scheduler.hasPendingRequests(this) ? 1 : 0; // best-effort; many schedulers could be extended to return exact size
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
        synchronized (scheduler) {
            if (!scheduler.hasPendingRequests(this)) { // No requests, stay idle
                status = ElevatorStatus.IDLE;
                direction = Direction.IDLE;
                return;
            }

            FloorNumber targetFloor = scheduler.peekNext(this); // Next floor according to scheduler
            if (targetFloor == null) { // nothing to do
                status = ElevatorStatus.IDLE;
                direction = Direction.IDLE;
                return;
            }

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
                scheduler.removeRequest(this, targetFloor); // Remove fulfilled request

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
                if (!scheduler.hasPendingRequests(this)) {
                    status = ElevatorStatus.IDLE; // No more requests
                    direction = Direction.IDLE;
                } else {
                    FloorNumber next = scheduler.peekNext(this);
                    if (next != null) {
                        if (next.getFloorValue() > currentFloor.getFloorValue()) direction = Direction.UP;
                        else if (next.getFloorValue() < currentFloor.getFloorValue()) direction = Direction.DOWN;
                    }
                }
            }
        }
    }
}
