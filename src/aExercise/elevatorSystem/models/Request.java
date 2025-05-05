package aScalerModule_08_LLD_3.assign_03.elevatorSystem.models;

import aScalerModule_08_LLD_3.assign_03.elevatorSystem.enums.Direction;
import aScalerModule_08_LLD_3.assign_03.elevatorSystem.enums.FloorNumber;

public class Request {
    private FloorNumber floor;
    private Direction direction;

    public Request(FloorNumber floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public FloorNumber getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}
