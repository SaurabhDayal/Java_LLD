package aMachineCoding.elevatorSystem.strategies;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.HallRequest;

import java.util.List;

public interface ElevatorDispatcher {
    Elevator selectElevator(List<Elevator> elevators, HallRequest hallRequest);
}
