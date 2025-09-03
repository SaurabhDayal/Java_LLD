package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;

public class Request {
    private final FloorNumber floor;
    private final Direction direction;

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
