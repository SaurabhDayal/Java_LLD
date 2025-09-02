package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private final List<Elevator> elevators;
    private final int totalFloors;
    private final ElevatorDispatcher dispatchStrategy;
    private FloorReachedListener floorReachedListener;

    public ElevatorSystem(ElevatorDispatcher dispatchStrategy) {
        this.totalFloors = FloorNumber.values().length;
        this.elevators = new ArrayList<>();
        this.dispatchStrategy = dispatchStrategy;
        for (ElevatorID elevatorId : ElevatorID.values()) {
            elevators.add(new Elevator(elevatorId));
        }
    }

    public void setFloorReachedListener(FloorReachedListener listener) {
        floorReachedListener = listener;
        for (Elevator elevator : elevators) {
            elevator.setFloorReachedListener(listener);
        }
    }

    public Elevator handleRequest(Request request) {
        Elevator selectedElevator = dispatchStrategy.selectElevator(elevators, request);
        if (selectedElevator != null) {
            selectedElevator.addRequest(request.getFloor());
            System.out.println("Request assigned to Elevator " + selectedElevator.getId());
        } else {
            System.out.println("No elevatorSystem available to handle request.");
        }
        return selectedElevator;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void startElevators() {
        for (Elevator elevator : elevators) {
            new Thread(elevator).start();
        }
    }

    public void stopElevators() {
        for (Elevator elevator : elevators) {
            elevator.stopElevator();
        }
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Elevator getElevator(ElevatorID elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId().equals(elevatorId)) {
                return elevator;
            }
        }
        throw new IllegalArgumentException("Elevator ID not found: " + elevatorId);
    }

    public FloorReachedListener getFloorReachedListener() {
        return floorReachedListener;
    }
}
