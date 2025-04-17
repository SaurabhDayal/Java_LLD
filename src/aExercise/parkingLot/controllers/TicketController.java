package aExercise.parkingLot.controllers;

import aExercise.parkingLot.dtos.*;
import aExercise.parkingLot.models.Ticket;
import aExercise.parkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto) {
        IssueTicketResponseDto responseDto = new IssueTicketResponseDto();

        try {
            Ticket ticket = ticketService.issueTicket(
                    requestDto.getVehicleNumber(),
                    requestDto.getOwnerName(),
                    requestDto.getGateId(),
                    requestDto.getOperatorId(),
                    requestDto.getVehicleType()
            );

            responseDto.setTicket(ticket);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

    public DeleteTicketResponseDto deleteTicket(DeleteTicketRequestDto requestDto) {
        DeleteTicketResponseDto responseDto = new DeleteTicketResponseDto();

        try {
            boolean deleted = ticketService.deleteTicket(requestDto.getTicketId());

            responseDto.setSuccess(deleted);
            responseDto.setResponseStatus(deleted ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
