package aMachineCoding.parkingLot.strategies;

import aMachineCoding.parkingLot.models.ParkingLot;
import aMachineCoding.parkingLot.models.ParkingSpot;
import aMachineCoding.parkingLot.models.Vehicle;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignParkingSpot(ParkingLot parkingLot, Vehicle vehicle);
}
