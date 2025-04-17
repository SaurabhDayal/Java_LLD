package aExercise.parkingLot.services;

import aExercise.parkingLot.exceptions.GateNotFoundException;
import aExercise.parkingLot.models.Gate;
import aExercise.parkingLot.models.ParkingLot;
import aExercise.parkingLot.models.Ticket;
import aExercise.parkingLot.models.Vehicle;
import aExercise.parkingLot.repositories.GateRepository;
import aExercise.parkingLot.repositories.VehicleRepository;
import aExercise.parkingLot.strategies.ParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;
    private HashMap<Long, Ticket> ticketStorage = new HashMap<>();
    private Long ticketIdCounter = 1L;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
    }

    public Ticket issueTicket(String vehicleNumber,
                              String ownerName,
                              Long gateId,
                              Long operatorId) throws GateNotFoundException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Get the Gate object
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);

        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gate id passed");
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);
        ticket.setOperator(gate.getOperator());

        // Check for existing vehicle or create a new one
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = null;
        if (optionalVehicle.isEmpty()) {
            // Vehicle is not present in the DB, create and save
            vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(ownerName);
            vehicle = vehicleRepository.save(vehicle); // Save the vehicle in the repository
        } else {
            vehicle = optionalVehicle.get();
        }
        ticket.setVehicle(vehicle);

        // Assign a parking spot based on strategy
        ParkingLot parkingLot = gate.getParkingLot();
        ticket.setParkingSpot(parkingSpotAssignmentStrategy.assignParkingSpot(parkingLot, vehicle));

        // Assign ticket ID and store the ticket
        ticket.setId(ticketIdCounter++);
        ticketStorage.put(ticket.getId(), ticket);

        return ticket;
    }

    public boolean deleteTicket(Long ticketId) {
        if (!ticketStorage.containsKey(ticketId)) {
            return false;
        }
        ticketStorage.remove(ticketId);
        return true;
    }
}

/*
Object Relation Mapping (ORM) -> Hibernate (Java + SpringBoot)
 */


/*
Service classes should be as general as possible.
 */
