package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.ParkingFloor;

import java.util.HashMap;
import java.util.Optional;

public class ParkingFloorRepository {
    private HashMap<Long, ParkingFloor> parkingFloorsById = new HashMap<>();
    private Long idCounter = 1L;

    public ParkingFloor save(ParkingFloor parkingFloor) {
        if (parkingFloor.getId() == null) {
            parkingFloor.setId(idCounter++);
        }
        parkingFloorsById.put(parkingFloor.getId(), parkingFloor);
        return parkingFloor;
    }

    public Optional<ParkingFloor> findById(Long id) {
        return Optional.ofNullable(parkingFloorsById.get(id));
    }

    public boolean delete(Long parkingFloorId) {
        if (parkingFloorsById.containsKey(parkingFloorId)) {
            parkingFloorsById.remove(parkingFloorId);
            return true;
        }
        return false;
    }
}
