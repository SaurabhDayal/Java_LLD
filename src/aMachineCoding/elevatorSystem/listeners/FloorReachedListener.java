package aMachineCoding.elevatorSystem.listeners;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.FloorNumber;

public interface FloorReachedListener {
    void onFloorReached(Elevator elevator, FloorNumber floor);
}