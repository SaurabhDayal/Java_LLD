package aMachineCoding.parkingLot.models;

public class Gate extends BaseModel {
    private int gateNumber;
    private Operator operator;
    private GateType gateType;
    private GateStatus gateStatus;
    private ParkingLot parkingLot;

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
