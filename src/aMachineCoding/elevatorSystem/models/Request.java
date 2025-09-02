package aMachineCoding.elevatorSystem.models;

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
