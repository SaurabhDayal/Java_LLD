package aScalerModule_08_LLD_3.assign_03.elevatorSystem.strategies;

import aScalerModule_08_LLD_3.assign_03.elevatorSystem.models.Elevator;
import aScalerModule_08_LLD_3.assign_03.elevatorSystem.models.Request;

import java.util.List;

public interface ElevatorDispatcher {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}
