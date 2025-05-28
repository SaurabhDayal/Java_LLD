package aMachineCoding.parkingLot.repositories;

import aMachineCoding.parkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Optional;

public class TicketRepository {
    private final HashMap<Long, Ticket> ticketsById = new HashMap<>();
    private final HashMap<String, Ticket> ticketsByNumber = new HashMap<>();
    private Long idCounter = 1L;

    public Ticket save(Ticket ticket) {
        if (ticketsByNumber.containsKey(ticket.getNumber())) {
            return ticketsByNumber.get(ticket.getNumber());
        }
        ticket.setId(idCounter++);
        ticket.setNumber("TICKET-" + ticket.getId());
        ticketsById.put(ticket.getId(), ticket);
        ticketsByNumber.put(ticket.getNumber(), ticket);
        return ticket;
    }

    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(ticketsById.get(id));
    }

    public Optional<Ticket> findByTicketNumber(String ticketNumber) {
        return Optional.ofNullable(ticketsByNumber.get(ticketNumber));
    }

    public boolean deleteById(Long ticketId) {
        if (ticketsById.containsKey(ticketId)) {
            Ticket ticket = ticketsById.remove(ticketId);
            ticketsByNumber.remove(ticket.getNumber());
            return true;
        }
        return false;
    }
}
