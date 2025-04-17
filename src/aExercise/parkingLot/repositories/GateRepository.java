package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    private HashMap<Long, Gate> gatesById = new HashMap<>();
    private Long idCounter = 1L;

    public Optional<Gate> findByGateId(Long id) {
        return Optional.ofNullable(gatesById.get(id));
    }

    public Gate save(Gate gate) {
        if (gate.getId() == null) {
            gate.setId(idCounter++);
        }
        gatesById.put(gate.getId(), gate);
        return gate;
    }
}
