package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.factories.SchedulerFactory;
import aMachineCoding.elevatorSystem.listeners.FloorReachedListener;
import aMachineCoding.elevatorSystem.models.enums.ElevatorID;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.enums.SchedulingStrategy;
import aMachineCoding.elevatorSystem.strategies.ElevatorDispatcher;
import aMachineCoding.elevatorSystem.strategies.ElevatorScheduler;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ElevatorSystem {

    private final List<Elevator> elevators;
    private final ElevatorDispatcher dispatchStrategy;

    public ElevatorSystem(ElevatorDispatcher dispatchStrategy, SchedulingStrategy schedulingStrategy) {

        this.dispatchStrategy = dispatchStrategy;

        // Build floors map once
        // Single source of truth for all floors & hall panels
        Map<FloorNumber, Floor> floors = new EnumMap<>(FloorNumber.class);
        for (FloorNumber fn : FloorNumber.values()) {
            floors.put(fn, new Floor(fn));
        }

        // Create elevators, each gets the same floors map
        this.elevators = new ArrayList<>();
        for (ElevatorID elevatorId : ElevatorID.values()) {
            ElevatorScheduler scheduler = SchedulerFactory.getScheduler(schedulingStrategy);
            elevators.add(new Elevator(elevatorId, scheduler, floors));
        }
    }

    public void setFloorReachedListener(FloorReachedListener listener) {
        for (Elevator elevator : elevators) {
            elevator.setFloorReachedListener(listener);
        }
    }

    public void handleRequest(HallRequest hallRequest) {
        Elevator selectedElevator = dispatchStrategy.selectElevator(elevators, hallRequest);
        if (selectedElevator != null) {
            selectedElevator.addRequest(hallRequest.floor());
            System.out.println("Request assigned to Elevator " + selectedElevator.getId());
        } else {
            System.out.println("No elevator available to handle request.");
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void startElevators() {
        for (Elevator elevator : elevators) {
            new Thread(elevator, "Elevator-" + elevator.getId()).start();
        }
    }

    public void stopElevators() {
        for (Elevator elevator : elevators) {
            elevator.stopElevator();
        }
    }
}
