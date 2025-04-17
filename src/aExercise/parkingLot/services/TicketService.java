package aExercise.parkingLot.services;

import aExercise.parkingLot.exceptions.GateNotFoundException;
import aExercise.parkingLot.models.Gate;
import aExercise.parkingLot.models.ParkingLot;
import aExercise.parkingLot.models.Ticket;
import aExercise.parkingLot.models.Vehicle;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.TicketRepository;
import aExercise.parkingLot.repositories.VehicleRepository;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;
    private final TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(String vehicleNumber,
                              String ownerName,
                              Long gateId,
                              Long operatorId) throws GateNotFoundException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Gate validation
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gate id passed");
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);
        ticket.setOperator(gate.getOperator());

        // Vehicle check or creation
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = optionalVehicle.orElseGet(() -> {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setNumber(vehicleNumber);
            newVehicle.setOwnerName(ownerName);
            return vehicleRepository.save(newVehicle);
        });

        ticket.setVehicle(vehicle);

        // Assign parking spot
        ParkingLot parkingLot = gate.getParkingLot();
        ticket.setParkingSpot(parkingSpotAssignmentStrategy.assignParkingSpot(parkingLot, vehicle));

        // Save ticket in repository
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }

    public boolean deleteTicket(Long ticketId) {
        return ticketRepository.deleteById(ticketId);
    }
}


/*
Object Relation Mapping (ORM) -> Hibernate (Java + SpringBoot)
 */


/*
Service classes should be as general as possible.
 */
