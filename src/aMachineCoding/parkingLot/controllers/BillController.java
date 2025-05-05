package aMachineCoding.parkingLot.controllers;

import aMachineCoding.parkingLot.dtos.IssueBillRequestDto;
import aMachineCoding.parkingLot.dtos.IssueBillResponseDto;
import aMachineCoding.parkingLot.dtos.ResponseStatus;
import aMachineCoding.parkingLot.models.Bill;
import aMachineCoding.parkingLot.services.BillService;

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
