package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.ElevatorStatus;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.panels.InsidePanel;
import aMachineCoding.elevatorSystem.models.panels.OutsidePanel;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.Map;

public class Elevator implements Runnable {

    private final ElevatorID id;                          // Unique ID of this elevator
    private FloorNumber currentFloor;                     // Current floor of the elevator
    private Direction direction;                          // Current moving direction
    private ElevatorStatus status;                        // Current status (IDLE, MOVING, etc.)
    private volatile boolean keepRunning;                 // Flag to control elevator thread
    private final InsidePanel insidePanel;                // Panel inside elevator with floor buttons
    private final ElevatorScheduler scheduler;            // Scheduler for this elevator
    private final Map<FloorNumber, Floor> buildingFloors; // Registry of all floors (for outside panels)
    private FloorReachedListener floorReachedListener;    // Listener for a floor reached events

    public Elevator(ElevatorID id, ElevatorScheduler scheduler, Map<FloorNumber, Floor> buildingFloors) {
        this.id = id;
        this.currentFloor = FloorNumber.GROUND;           // Start at the ground floor
        this.direction = Direction.UP;                    // Default initial direction
        this.status = ElevatorStatus.IDLE;                // Initially idle
        this.keepRunning = true;                          // Thread should run
        this.insidePanel = new InsidePanel(this); // Attach inside panel
        this.scheduler = scheduler;
        this.buildingFloors = buildingFloors;             // Store building floors map
    }

    public InsidePanel getInsidePanel() {
        return insidePanel; // Return the attached inside panel
    }

    public void setFloorReachedListener(FloorReachedListener listener) {
        this.floorReachedListener = listener; // Register floor reached listener
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

    public void stopElevator() {
        this.keepRunning = false; // Stop the elevator thread
    }

    public void addRequest(FloorNumber floor) {
        synchronized (scheduler) {
            if (!scheduler.hasPendingRequests(this) || !isDuplicate(floor)) {
                scheduler.addRequest(this, floor); // Delegate to scheduler
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
        // Best-effort duplicate detection
        FloorNumber p = scheduler.peekNext(this);
        return p != null && p.getFloorValue() == floor.getFloorValue();
    }

    public int getPendingRequestCount() {
        return scheduler.hasPendingRequests(this) ? 1 : 0;
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
            if (targetFloor == null) {
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

                // Reset outside panel button for this floor
                Floor servedFloor = buildingFloors.get(currentFloor);
                if (servedFloor != null) {
                    OutsidePanel outsidePanel = servedFloor.getOutsidePanel();
                    if (direction == Direction.UP) {
                        outsidePanel.getUpButton().reset();
                    } else if (direction == Direction.DOWN) {
                        outsidePanel.getDownButton().reset();
                    }
                }

                // Update status and direction for next request
                if (!scheduler.hasPendingRequests(this)) {
                    status = ElevatorStatus.IDLE;
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
