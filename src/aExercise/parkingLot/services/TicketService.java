package aExercise.parkingLot.services;

import aExercise.parkingLot.exceptions.GateNotFoundException;
import aExercise.parkingLot.exceptions.OperatorNotFoundException;
import aExercise.parkingLot.models.*;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.OperatorRepository;
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
    private final OperatorRepository operatorRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy,
                         TicketRepository ticketRepository, OperatorRepository operatorRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
        this.operatorRepository = operatorRepository;
    }

    public Ticket issueTicket(String vehicleNumber,
                              String ownerName,
                              Long gateId,
                              Long operatorId,
                              String vehicleType) throws GateNotFoundException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Gate validation
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gate id passed");
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);

        // Operator validation
        Optional<Operator> optionalOperator = operatorRepository.findById(operatorId);
        if (optionalOperator.isEmpty()) {
            throw new OperatorNotFoundException("Operator not found for the given operatorId");
        }

        Operator operator = optionalOperator.get();
        ticket.setOperator(operator);

        // Vehicle check or creation
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = optionalVehicle.orElseGet(() -> {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setNumber(vehicleNumber);
            newVehicle.setOwnerName(ownerName);
            newVehicle.setVehicleType(VehicleType.valueOf(vehicleType));  // Set vehicle type
            return vehicleRepository.save(newVehicle);
        });

        ticket.setVehicle(vehicle);

        // Assign parking spot
        ParkingLot parkingLot = gate.getParkingLot();
        ticket.setParkingSpot(parkingSpotAssignmentStrategy.assignParkingSpot(parkingLot, vehicle));

        // Generate and set a unique ticket number
        ticket.setNumber("TICKET-" + ticket.getId());

        // Save ticket in repository
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }

    public boolean deleteTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isPresent()) {
            return ticketRepository.deleteById(ticketId);
        }
        return false;  // Ticket not found
    }
}
