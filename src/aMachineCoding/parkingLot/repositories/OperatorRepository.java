package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Operator;

import java.util.HashMap;
import java.util.Optional;

public class OperatorRepository {
    private HashMap<Long, Operator> operatorsById = new HashMap<>();
    private Long idCounter = 1L;

    public Operator save(Operator operator) {
        if (operator.getId() == null) {
            operator.setId(idCounter++);
        }
        operatorsById.put(operator.getId(), operator);
        return operator;
    }

    public Optional<Operator> findById(Long id) {
        return Optional.ofNullable(operatorsById.get(id));
    }

    public Optional<Operator> findByEmpId(int empId) {
        return operatorsById.values().stream()
                .filter(operator -> operator.getEmpId() == empId)
                .findFirst();
    }

    public boolean delete(Long operatorId) {
        if (operatorsById.containsKey(operatorId)) {
            operatorsById.remove(operatorId);
            return true;
        }
        return false;
    }
}
