package aExercise.parkingLot.controllers;

import aExercise.parkingLot.dtos.IssueBillRequestDto;
import aExercise.parkingLot.dtos.IssueBillResponseDto;
import aExercise.parkingLot.dtos.ResponseStatus;
import aExercise.parkingLot.models.Bill;
import aExercise.parkingLot.services.BillService;

public class BillController {
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // Issue a Bill for a Ticket
    public IssueBillResponseDto issueBill(IssueBillRequestDto requestDto) {
        IssueBillResponseDto responseDto = new IssueBillResponseDto();

        try {
            Bill bill = billService.issueBill(
                    requestDto.getTicketId(),
                    requestDto.getAmount(),
                    requestDto.getPaymentStatus(),
                    requestDto.getPaymentMode(),
                    requestDto.getReferenceNumber(),
                    requestDto.getGateId(),
                    requestDto.getOperatorId()
            );

            responseDto.setBill(bill);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
