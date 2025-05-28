package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.ParkingSpot;
import aMachineCoding.parkingLot.models.ParkingSpotStatus;

import java.util.HashMap;
import java.util.Optional;

public class ParkingSpotRepository {
    private final HashMap<Long, ParkingSpot> parkingSpotsById = new HashMap<>();
    private final HashMap<Integer, ParkingSpot> parkingSpotsByNumber = new HashMap<>();
    private Long idCounter = 1L;

    public ParkingSpot save(ParkingSpot parkingSpot) {
        if (parkingSpotsByNumber.containsKey(parkingSpot.getSpotNumber())) {
            return parkingSpotsByNumber.get(parkingSpot.getSpotNumber());
        }
        parkingSpot.setId(idCounter++);
        parkingSpotsById.put(parkingSpot.getId(), parkingSpot);
        parkingSpotsByNumber.put(parkingSpot.getSpotNumber(), parkingSpot);
        return parkingSpot;
    }

    public Optional<ParkingSpot> findById(Long id) {
        return Optional.ofNullable(parkingSpotsById.get(id));
    }

    public Optional<ParkingSpot> findBySpotNumber(int spotNumber) {
        return Optional.ofNullable(parkingSpotsByNumber.get(spotNumber));
    }

    public void updateStatus(Long id, ParkingSpotStatus status) {
        ParkingSpot parkingSpot = parkingSpotsById.get(id);
        if (parkingSpot != null) {
            parkingSpot.setParkingSpotStatus(status);
        }
    }

    public HashMap<Long, ParkingSpot> getAll() {
        return parkingSpotsById;
    }
}
