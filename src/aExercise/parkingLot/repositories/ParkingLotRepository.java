package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLotRepository {
    // In-memory storage for Parking Lots
    private HashMap<Long, ParkingLot> parkingLots = new HashMap<>();
    private Long idCounter = 1L; // ID counter for Parking Lots

    // Find a ParkingLot by its ID
    public Optional<ParkingLot> findById(Long id) {
        return Optional.ofNullable(parkingLots.get(id));
    }

    // Add a ParkingLot to the repository
    public ParkingLot save(ParkingLot parkingLot) {
        // If the parking lot doesn't have an ID, assign a new unique ID
        if (parkingLot.getId() == null) {
            parkingLot.setId(idCounter++);
        }
        parkingLots.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }
}
