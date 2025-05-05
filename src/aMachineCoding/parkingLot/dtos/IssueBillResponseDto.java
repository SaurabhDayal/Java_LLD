package aMachineCoding.parkingLot.dtos;

import aMachineCoding.parkingLot.models.Bill;

public class IssueBillResponseDto {
    private Bill bill;
    private ResponseStatus responseStatus;

    // Getters and Setters
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
