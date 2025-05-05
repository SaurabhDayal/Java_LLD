package aMachineCoding.parkingLot.dtos;

public class DeleteTicketResponseDto {
    private boolean success;
    private ResponseStatus responseStatus;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
