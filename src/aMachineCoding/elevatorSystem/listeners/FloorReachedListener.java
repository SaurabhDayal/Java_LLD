package aMachineCoding.elevatorSystem.listeners;

import aMachineCoding.elevatorSystem.enums.FloorNumber;
import aMachineCoding.elevatorSystem.models.Elevator;

public interface FloorReachedListener {
    void onFloorReached(Elevator elevator, FloorNumber floor);
}