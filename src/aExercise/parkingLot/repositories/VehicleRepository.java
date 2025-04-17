package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Vehicle;

import java.util.HashMap;
import java.util.Optional;

public class VehicleRepository {

    // In-memory storage for vehicles
    private HashMap<String, Vehicle> vehiclesByNumber = new HashMap<>();
    private HashMap<Long, Vehicle> vehiclesById = new HashMap<>();
    private Long idCounter = 1L;

    // Save method to store the vehicle
    public Vehicle save(Vehicle vehicle) {
        // If vehicle already exists, we simply return it
        if (vehiclesByNumber.containsKey(vehicle.getNumber())) {
            return vehiclesByNumber.get(vehicle.getNumber());
        }

        // Assign an ID to the vehicle before saving it
        vehicle.setId(idCounter++);
        vehiclesByNumber.put(vehicle.getNumber(), vehicle);
        vehiclesById.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    // Find vehicle by its ID
    public Optional<Vehicle> findByVehicleId(Long id) {
        return Optional.ofNullable(vehiclesById.get(id));
    }

    // Find vehicle by its number
    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        return Optional.ofNullable(vehiclesByNumber.get(vehicleNumber));
    }
}
