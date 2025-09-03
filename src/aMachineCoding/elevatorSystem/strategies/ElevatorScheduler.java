package aMachineCoding.elevatorSystem.strategies;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;

public interface ElevatorScheduler {
    void addRequest(Elevator elevator, FloorNumber floor);

    FloorNumber peekNext(Elevator elevator);

    void removeRequest(Elevator elevator, FloorNumber floor);

    boolean hasPendingRequests(Elevator elevator);
}
