package aMachineCoding.elevatorSystem.models;

import aMachineCoding.elevatorSystem.models.enums.Direction;
import aMachineCoding.elevatorSystem.models.enums.FloorNumber;

public record HallRequest(FloorNumber floor, Direction direction) {
}
