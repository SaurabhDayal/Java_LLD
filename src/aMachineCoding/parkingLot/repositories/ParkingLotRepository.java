package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLotRepository {
    private HashMap<Long, ParkingLot> parkingLots = new HashMap<>();
    private Long idCounter = 1L;

    public ParkingLot save(ParkingLot parkingLot) {
        if (parkingLot.getId() == null) {
            parkingLot.setId(idCounter++);
        }
        parkingLots.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }

    public Optional<ParkingLot> findById(Long id) {
        return Optional.ofNullable(parkingLots.get(id));
    }
}
