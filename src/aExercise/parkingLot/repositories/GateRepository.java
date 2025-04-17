package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    // In-memory storage for Gates
    private HashMap<Long, Gate> gatesById = new HashMap<>();
    private Long idCounter = 1L;  // ID counter for Gates

    // Find a Gate by ID
    public Optional<Gate> findByGateId(Long id) {
        return Optional.ofNullable(gatesById.get(id));
    }

    // Add a Gate
    public Gate addGate(Gate gate) {
        // If gate does not already have an ID, assign a new unique ID
        if (gate.getId() == null) {
            gate.setId(idCounter++);
        }
        gatesById.put(gate.getId(), gate);
        return gate;
    }
}
