package aExercise.parkingLot.strategies;

import aExercise.parkingLot.models.ParkingLot;
import aExercise.parkingLot.models.ParkingSpot;
import aExercise.parkingLot.models.Vehicle;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy {
    @Override
    public ParkingSpot assignParkingSpot(ParkingLot parkingLot, Vehicle vehicle) {
        return new ParkingSpot();
    }
}
