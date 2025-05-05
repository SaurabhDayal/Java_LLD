package aExercise.elevatorSystem.models;

import aExercise.elevatorSystem.enums.Direction;
import aExercise.elevatorSystem.enums.ElevatorID;
import aExercise.elevatorSystem.enums.ElevatorStatus;
import aExercise.elevatorSystem.enums.FloorNumber;
import aExercise.elevatorSystem.listeners.FloorReachedListener;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator implements Runnable {
    private final ElevatorID id;
    private FloorNumber currentFloor;
    private Direction direction;
    private ElevatorStatus status;
    private final Queue<FloorNumber> requests;
    private volatile boolean keepRunning;
    private FloorReachedListener floorReachedListener;

    public Elevator(ElevatorID id) {
        this.id = id;
        this.currentFloor = FloorNumber.F_0;
        this.direction = Direction.UP;
        this.status = ElevatorStatus.IDLE;
        this.requests = new LinkedList<>();
        this.keepRunning = true;
    }

    public void setFloorReachedListener(FloorReachedListener listener) {
        this.floorReachedListener = listener;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorID getId() {
        return id;
    }

    public FloorNumber getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public void stopElevator() {
        this.keepRunning = false;
    }

    public void addRequest(FloorNumber floor) {
        synchronized (requests) {
            if (!requests.contains(floor)) {
                requests.add(floor);
                status = ElevatorStatus.MOVING;

                if (floor.getFloorValue() > currentFloor.getFloorValue()) {
                    direction = Direction.UP;
                } else if (floor.getFloorValue() < currentFloor.getFloorValue()) {
                    direction = Direction.DOWN;
                }
            }
        }
    }

    public Queue<FloorNumber> getRequests() {
        return requests;
    }

    public boolean hasPendingRequestAbove(FloorNumber floor) {
        synchronized (requests) {
            for (FloorNumber req : requests) {
                if (req.getFloorValue() > floor.getFloorValue()) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean hasPendingRequestBelow(FloorNumber floor) {
        synchronized (requests) {
            for (FloorNumber req : requests) {
                if (req.getFloorValue() < floor.getFloorValue()) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void run() {
        while (keepRunning) {
            move();
            try {
                Thread.sleep(1000); // simulate time taken between floors
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void move() {
        synchronized (requests) {
            if (requests.isEmpty()) {
                status = ElevatorStatus.IDLE;
                return;
            }

            FloorNumber targetFloor = requests.peek();

            if (currentFloor.getFloorValue() < targetFloor.getFloorValue()) {
                currentFloor = FloorNumber.values()[currentFloor.getFloorValue() + 1];
                direction = Direction.UP;
            } else if (currentFloor.getFloorValue() > targetFloor.getFloorValue()) {
                currentFloor = FloorNumber.values()[currentFloor.getFloorValue() - 1];
                direction = Direction.DOWN;
            }

            System.out.printf("    - Elevator %s at floor %s, moving towards %s%n",
                    id, currentFloor, targetFloor);

            if (currentFloor == targetFloor) {
                System.out.printf("    - Elevator %s reached target floor %s%n", id, currentFloor);
                requests.poll();

                if (floorReachedListener != null) {
                    floorReachedListener.onFloorReached(this, currentFloor);
                }

                if (requests.isEmpty()) {
                    status = ElevatorStatus.IDLE;
                } else {
                    FloorNumber next = requests.peek();
                    if (next.getFloorValue() > currentFloor.getFloorValue()) {
                        direction = Direction.UP;
                    } else if (next.getFloorValue() < currentFloor.getFloorValue()) {
                        direction = Direction.DOWN;
                    }
                }
            }
        }
    }
}
