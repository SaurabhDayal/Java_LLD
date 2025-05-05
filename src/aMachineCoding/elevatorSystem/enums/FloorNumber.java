package aMachineCoding.elevatorSystem.enums;

public enum FloorNumber {
    F_0(0),
    F_1(1),
    F_2(2),
    F_3(3),
    F_4(4),
    F_5(5),
    F_6(6),
    F_7(7),
    F_8(8),
    F_9(9),
    F_10(10),
    F_11(11),
    F_12(12),
    F_13(13),
    F_14(14),
    F_15(15);

    private final int floorValue;

    FloorNumber(int floorValue) {
        this.floorValue = floorValue;
    }

    public int getFloorValue() {
        return floorValue;
    }

    public boolean isAboveGroundFloor() {
        return floorValue > 0;
    }

    @Override
    public String toString() {
        return String.valueOf(floorValue);
    }
}
