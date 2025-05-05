package aMachineCoding.parkingLot.factories;

import aMachineCoding.parkingLot.models.ParkingSpotStrategyType;
import aMachineCoding.parkingLot.strategies.NearestSpotAssignmentStrategy;
import aMachineCoding.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingLotStrategy(ParkingSpotStrategyType parkingSpotStrategyType) {
        if (parkingSpotStrategyType.equals(ParkingSpotStrategyType.NEAREST)) {
            return new NearestSpotAssignmentStrategy();
        }
        return null;
    }
}
