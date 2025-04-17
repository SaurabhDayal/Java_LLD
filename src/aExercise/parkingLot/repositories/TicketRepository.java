package aExercise.parkingLot.repositories;

import aExercise.parkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Optional;

public class TicketRepository {
    // In-memory storage for tickets by ticket number and ID
    private HashMap<Long, Ticket> ticketsById = new HashMap<>();
    private HashMap<String, Ticket> ticketsByNumber = new HashMap<>();
    private Long idCounter = 1L;

    // Save a Ticket
    public Ticket save(Ticket ticket) {
        // If the ticket already exists by number, return the existing ticket
        if (ticketsByNumber.containsKey(ticket.getNumber())) {
            return ticketsByNumber.get(ticket.getNumber());
        }

        // Assign an ID to the ticket before saving it
        ticket.setId(idCounter++);
        ticketsById.put(ticket.getId(), ticket);
        ticketsByNumber.put(ticket.getNumber(), ticket);
        return ticket;
    }

    // Find a Ticket by its ID
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(ticketsById.get(id));
    }

    // Find a Ticket by its number
    public Optional<Ticket> findByTicketNumber(String ticketNumber) {
        return Optional.ofNullable(ticketsByNumber.get(ticketNumber));
    }

    // Delete a Ticket by its ID
    public boolean deleteById(Long ticketId) {
        if (ticketsById.containsKey(ticketId)) {
            Ticket ticket = ticketsById.remove(ticketId);
            ticketsByNumber.remove(ticket.getNumber());
            return true;
        }
        return false;
    }
}
