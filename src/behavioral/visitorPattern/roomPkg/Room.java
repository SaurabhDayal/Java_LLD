package behavioral.visitorPattern.roomPkg;

import behavioral.visitorPattern.visitorPkg.RoomVisitor;

// Element
public interface Room {
    void accept(RoomVisitor visitor);
}
