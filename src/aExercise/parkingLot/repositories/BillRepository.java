package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Bill;

import java.util.HashMap;
import java.util.Optional;

public class BillRepository {
    // In-memory storage for Bills
    private HashMap<Long, Bill> billsById = new HashMap<>();
    private Long idCounter = 1L;

    // Save method to store a Bill
    public Bill save(Bill bill) {
        // Assign an ID to the bill before saving it
        bill.setId(idCounter++);
        billsById.put(bill.getId(), bill);
        return bill;
    }

    // Find Bill by ID
    public Optional<Bill> findById(Long id) {
        return Optional.ofNullable(billsById.get(id));
    }

    // Find Bill by Ticket ID
    public Optional<Bill> findByTicketId(Long ticketId) {
        return billsById.values().stream()
                .filter(bill -> bill.getTicket() != null && bill.getTicket().getId().equals(ticketId))
                .findFirst();
    }

    // Delete a Bill
    public boolean delete(Long billId) {
        if (billsById.containsKey(billId)) {
            billsById.remove(billId);
            return true;
        }
        return false;
    }
}
