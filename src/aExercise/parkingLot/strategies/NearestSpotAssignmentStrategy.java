package aExercise.parkingLot.strategies;

import aExercise.parkingLot.exceptions.ParkingSpotNotAvailableException;
import aExercise.parkingLot.models.*;

import java.util.List;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy {

    @Override
    public ParkingSpot assignParkingSpot(ParkingLot parkingLot, Vehicle vehicle) {
        // Loop through all parking floors in the parking lot
        for (ParkingFloor floor : parkingLot.getParkingFloors()) {
            List<ParkingSpot> spots = floor.getParkingSpots();

            // Loop through all parking spots in the current floor
            for (ParkingSpot spot : spots) {
                // Check if the spot is empty and supports the vehicle type
                if (spot.getParkingSpotStatus() == ParkingSpotStatus.EMPTY &&
                        spot.getSupportedVehicleTypes().contains(vehicle.getVehicleType())) {
                    // Mark the spot as filled and assign it to the vehicle
                    spot.setParkingSpotStatus(ParkingSpotStatus.FILLED);
                    return spot;  // Return the assigned parking spot
                }
            }
        }

        // If no available spots, throw a custom exception
        throw new ParkingSpotNotAvailableException("No parking spots available for the vehicle type: " + vehicle.getVehicleType());
    }
}
