package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.ParkingSpot;
import aExercise.parkingLot.models.ParkingSpotStatus;

import java.util.HashMap;
import java.util.Optional;

public class ParkingSpotRepository {
    // In-memory storage for parking spots by ID and spot number
    private HashMap<Long, ParkingSpot> parkingSpotsById = new HashMap<>();
    private HashMap<Integer, ParkingSpot> parkingSpotsByNumber = new HashMap<>();
    private Long idCounter = 1L;

    // Save a ParkingSpot
    public ParkingSpot save(ParkingSpot parkingSpot) {
        // If the spot already exists, return the existing one
        if (parkingSpotsByNumber.containsKey(parkingSpot.getSpotNumber())) {
            return parkingSpotsByNumber.get(parkingSpot.getSpotNumber());
        }

        // Assign a unique ID to the parking spot before saving it
        parkingSpot.setId(idCounter++);
        parkingSpotsById.put(parkingSpot.getId(), parkingSpot);
        parkingSpotsByNumber.put(parkingSpot.getSpotNumber(), parkingSpot);
        return parkingSpot;
    }

    // Find a ParkingSpot by ID
    public Optional<ParkingSpot> findById(Long id) {
        return Optional.ofNullable(parkingSpotsById.get(id));
    }

    // Find a ParkingSpot by spot number
    public Optional<ParkingSpot> findBySpotNumber(int spotNumber) {
        return Optional.ofNullable(parkingSpotsByNumber.get(spotNumber));
    }

    // Update the ParkingSpot status
    public void updateStatus(Long id, ParkingSpotStatus status) {
        ParkingSpot parkingSpot = parkingSpotsById.get(id);
        if (parkingSpot != null) {
            parkingSpot.setParkingSpotStatus(status);
        }
    }

    // Get all ParkingSpots
    public HashMap<Long, ParkingSpot> getAll() {
        return parkingSpotsById;
    }
}
