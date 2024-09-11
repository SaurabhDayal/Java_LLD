package behavioral.visitorPattern.visitorPkg;

import behavioral.visitorPattern.roomPkg.Bedroom;
import behavioral.visitorPattern.roomPkg.Kitchen;
import behavioral.visitorPattern.roomPkg.LivingRoom;

public class CleaningVisitor implements RoomVisitor {
    @Override
    public void visit(Bedroom bedroom) {
        System.out.println("Cleaning the bedroom: " + bedroom.getName());
    }

    @Override
    public void visit(Kitchen kitchen) {
        System.out.println("Cleaning the kitchen: " + kitchen.getName());
    }

    @Override
    public void visit(LivingRoom livingRoom) {
        System.out.println("Cleaning the living room: " + livingRoom.getName());
    }
}