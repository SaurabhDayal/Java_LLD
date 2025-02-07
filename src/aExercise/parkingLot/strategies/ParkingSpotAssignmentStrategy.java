package aExercise.parkingLot.strategies;

import aExercise.parkingLot.models.ParkingLot;
import aExercise.parkingLot.models.ParkingSpot;
import aExercise.parkingLot.models.Vehicle;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignParkingSpot(ParkingLot parkingLot, Vehicle vehicle);
}
