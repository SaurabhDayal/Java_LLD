package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Vehicle;

import java.util.HashMap;
import java.util.Optional;

public class VehicleRepository {
    private final HashMap<String, Vehicle> vehiclesByNumber = new HashMap<>();
    private final HashMap<Long, Vehicle> vehiclesById = new HashMap<>();
    private Long idCounter = 1L;

    public Vehicle save(Vehicle vehicle) {
        if (vehiclesByNumber.containsKey(vehicle.getNumber())) {
            return vehiclesByNumber.get(vehicle.getNumber());
        }
        vehicle.setId(idCounter++);
        vehiclesByNumber.put(vehicle.getNumber(), vehicle);
        vehiclesById.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public Optional<Vehicle> findByVehicleId(Long id) {
        return Optional.ofNullable(vehiclesById.get(id));
    }

    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        return Optional.ofNullable(vehiclesByNumber.get(vehicleNumber));
    }
}
