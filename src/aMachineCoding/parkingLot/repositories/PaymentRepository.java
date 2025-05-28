package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Payment;

import java.util.HashMap;
import java.util.Optional;

public class PaymentRepository {
    private HashMap<Long, Payment> paymentsById = new HashMap<>();
    private Long idCounter = 1L;

    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(idCounter++);
        }
        paymentsById.put(payment.getId(), payment);
        return payment;
    }

    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(paymentsById.get(id));
    }

    public Optional<Payment> findByReferenceNumber(String referenceNumber) {
        return paymentsById.values().stream()
                .filter(payment -> payment.getReferenceNumber().equals(referenceNumber))
                .findFirst();
    }

    public boolean delete(Long paymentId) {
        if (paymentsById.containsKey(paymentId)) {
            paymentsById.remove(paymentId);
            return true;
        }
        return false;
    }
}
