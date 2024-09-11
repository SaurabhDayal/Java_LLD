package behavioral.visitorPattern.roomPkg;

import behavioral.visitorPattern.visitorPkg.RoomVisitor;

public interface Room {
    void accept(RoomVisitor visitor);
}
