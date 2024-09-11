package behavioral.visitorPattern.visitorPkg;

import behavioral.visitorPattern.roomPkg.Bedroom;
import behavioral.visitorPattern.roomPkg.Kitchen;
import behavioral.visitorPattern.roomPkg.LivingRoom;

public class InspectionVisitor implements RoomVisitor {
    @Override
    public void visit(Bedroom bedroom) {
        System.out.println("Inspecting the bedroom: " + bedroom.getName());
    }

    @Override
    public void visit(Kitchen kitchen) {
        System.out.println("Inspecting the kitchen: " + kitchen.getName());
    }

    @Override
    public void visit(LivingRoom livingRoom) {
        System.out.println("Inspecting the living room: " + livingRoom.getName());
    }
}
