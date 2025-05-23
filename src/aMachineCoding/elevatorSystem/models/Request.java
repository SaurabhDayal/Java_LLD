package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.enums.Direction;
import aMachineCoding.elevatorSystem.enums.FloorNumber;

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
