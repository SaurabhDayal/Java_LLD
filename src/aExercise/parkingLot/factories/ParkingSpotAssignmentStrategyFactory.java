package aExercise.parkingLot.factories;

import aExercise.parkingLot.models.ParkingSpotStrategyType;
import aExercise.parkingLot.strategies.NearestSpotAssignmentStrategy;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy getParkingLotStrategy(ParkingSpotStrategyType
                                                                              parkingSpotStrategyType) {
        if (parkingSpotStrategyType.equals(ParkingSpotStrategyType.NEAREST)) {
            return new NearestSpotAssignmentStrategy();
        }
        return null;
    }
}
