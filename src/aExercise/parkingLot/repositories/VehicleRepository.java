package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Vehicle;

import java.util.Optional;

public class VehicleRepository {

    //Output Vehicle object will have id present.
    public Vehicle save(Vehicle vehicle) {
        return null;
    }

    public Optional<Vehicle> findByVehicleId(Long id) {
        return Optional.empty();
    }

    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        return Optional.empty();
    }
}
