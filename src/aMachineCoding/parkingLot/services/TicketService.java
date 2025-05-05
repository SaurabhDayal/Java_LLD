package aMachineCoding.parkingLot.services;

import aMachineCoding.parkingLot.exceptions.GateNotFoundException;
import aMachineCoding.parkingLot.exceptions.OperatorNotFoundException;
import aMachineCoding.parkingLot.exceptions.ParkingSpotNotAvailableException;
import aMachineCoding.parkingLot.models.*;
import aMachineCoding.parkingLot.repositories.GateRepository;
import aMachineCoding.parkingLot.repositories.OperatorRepository;
import aMachineCoding.parkingLot.repositories.TicketRepository;
import aMachineCoding.parkingLot.repositories.VehicleRepository;
import aMachineCoding.parkingLot.strategies.ParkingSpotAssignmentStrategy;

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
                              String vehicleType) throws GateNotFoundException, OperatorNotFoundException {

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

        // Assign parking spot and handle no availability
        ParkingLot parkingLot = gate.getParkingLot();
        ParkingSpot assignedSpot = null;
        try {
            assignedSpot = parkingSpotAssignmentStrategy.assignParkingSpot(parkingLot, vehicle);
        } catch (ParkingSpotNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
            // Handle the exception (either recover or notify)
            return null;  // Return null or another value depending on your recovery strategy
        }
        ticket.setParkingSpot(assignedSpot);

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
