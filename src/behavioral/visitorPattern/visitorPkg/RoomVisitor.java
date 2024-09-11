package behavioral.visitorPattern.visitorPkg;

import behavioral.visitorPattern.roomPkg.Bedroom;
import behavioral.visitorPattern.roomPkg.Kitchen;
import behavioral.visitorPattern.roomPkg.LivingRoom;

public interface RoomVisitor {
    void visit(Bedroom bedroom);

    void visit(Kitchen kitchen);

    void visit(LivingRoom livingRoom);
}