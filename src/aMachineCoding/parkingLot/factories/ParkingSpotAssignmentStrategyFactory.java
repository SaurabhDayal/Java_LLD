package aMachineCoding.parkingLot.factories;

import aMachineCoding.parkingLot.models.ParkingSpotStrategyType;
import aMachineCoding.parkingLot.strategies.NearestSpotAssignmentStrategy;
import aMachineCoding.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingLotStrategy(ParkingSpotStrategyType parkingSpotStrategyType) {
        switch (parkingSpotStrategyType) {
            case NEAREST:
                return new NearestSpotAssignmentStrategy();
            // Add more cases here when more strategies are added
            default:
                return null;
        }
    }
}
