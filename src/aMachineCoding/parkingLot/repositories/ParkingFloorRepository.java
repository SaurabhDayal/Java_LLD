package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.ParkingFloor;

import java.util.HashMap;
import java.util.Optional;

public class ParkingFloorRepository {
    // In-memory storage for ParkingFloors
    private HashMap<Long, ParkingFloor> parkingFloorsById = new HashMap<>();
    private Long idCounter = 1L;

    // Save method to store a ParkingFloor
    public ParkingFloor save(ParkingFloor parkingFloor) {
        // Assign an ID to the parking floor before saving it
        parkingFloor.setId(idCounter++);
        parkingFloorsById.put(parkingFloor.getId(), parkingFloor);
        return parkingFloor;
    }

    // Find ParkingFloor by ID
    public Optional<ParkingFloor> findById(Long id) {
        return Optional.ofNullable(parkingFloorsById.get(id));
    }

    // Delete a ParkingFloor by ID
    public boolean delete(Long parkingFloorId) {
        if (parkingFloorsById.containsKey(parkingFloorId)) {
            parkingFloorsById.remove(parkingFloorId);
            return true;
        }
        return false;
    }
}
