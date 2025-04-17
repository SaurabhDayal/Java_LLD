package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Operator;

import java.util.HashMap;
import java.util.Optional;

public class OperatorRepository {
    // In-memory storage for Operators
    private HashMap<Long, Operator> operatorsById = new HashMap<>();
    private Long idCounter = 1L;

    // Save method to store an Operator
    public Operator save(Operator operator) {
        // Assign an ID to the operator before saving it
        operator.setId(idCounter++);
        operatorsById.put(operator.getId(), operator);
        return operator;
    }

    // Find Operator by ID
    public Optional<Operator> findById(Long id) {
        return Optional.ofNullable(operatorsById.get(id));
    }

    // Find Operator by Employee ID
    public Optional<Operator> findByEmpId(int empId) {
        return operatorsById.values().stream()
                .filter(operator -> operator.getEmpId() == empId)
                .findFirst();
    }

    // Delete an Operator by ID
    public boolean delete(Long operatorId) {
        if (operatorsById.containsKey(operatorId)) {
            operatorsById.remove(operatorId);
            return true;
        }
        return false;
    }
}
