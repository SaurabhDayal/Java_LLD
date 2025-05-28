package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Bill;

import java.util.HashMap;
import java.util.Optional;

public class BillRepository {
    private HashMap<Long, Bill> billsById = new HashMap<>();
    private Long idCounter = 1L;

    public Bill save(Bill bill) {
        if (bill.getId() == null) {
            bill.setId(idCounter++);
        }
        billsById.put(bill.getId(), bill);
        return bill;
    }

    public Optional<Bill> findById(Long id) {
        return Optional.ofNullable(billsById.get(id));
    }

    public Optional<Bill> findByTicketId(Long ticketId) {
        return billsById.values().stream()
                .filter(bill -> bill.getTicket() != null && bill.getTicket().getId().equals(ticketId))
                .findFirst();
    }

    public boolean delete(Long billId) {
        if (billsById.containsKey(billId)) {
            billsById.remove(billId);
            return true;
        }
        return false;
    }
}
