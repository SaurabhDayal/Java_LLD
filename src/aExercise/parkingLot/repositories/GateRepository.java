package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    //Gate Table
    private HashMap<Long, Gate> gates = new HashMap<>();

    //CRUD operations on Gate table.
    //select * from gates where id =
    public Optional<Gate> findByGateId(Long id) {
        return Optional.of(gates.get(id));
    }
}
