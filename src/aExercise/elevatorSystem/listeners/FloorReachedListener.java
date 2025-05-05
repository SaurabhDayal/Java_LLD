package aScalerModule_08_LLD_3.assign_03.elevatorSystem.listeners;

import aScalerModule_08_LLD_3.assign_03.elevatorSystem.enums.FloorNumber;
import aScalerModule_08_LLD_3.assign_03.elevatorSystem.models.Elevator;

public interface FloorReachedListener {
    void onFloorReached(Elevator elevator, FloorNumber floor);
}