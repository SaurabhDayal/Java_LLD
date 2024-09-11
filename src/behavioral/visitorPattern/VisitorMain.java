package behavioral.visitorPattern;

import behavioral.visitorPattern.roomPkg.Bedroom;
import behavioral.visitorPattern.roomPkg.Kitchen;
import behavioral.visitorPattern.roomPkg.LivingRoom;
import behavioral.visitorPattern.roomPkg.Room;
import behavioral.visitorPattern.visitorPkg.CleaningVisitor;
import behavioral.visitorPattern.visitorPkg.InspectionVisitor;
import behavioral.visitorPattern.visitorPkg.RoomVisitor;

public class VisitorMain {
    public static void main(String[] args) {
        // Create room objects
        Room bedroom = new Bedroom("Master Bedroom");
        Room kitchen = new Kitchen("Main Kitchen");
        Room livingRoom = new LivingRoom("Family Living Room");

        // Create visitors
        RoomVisitor cleaningVisitor = new CleaningVisitor();
        RoomVisitor inspectionVisitor = new InspectionVisitor();

        // Perform operations on rooms
        System.out.println("Cleaning Rooms:");
        bedroom.accept(cleaningVisitor);
        kitchen.accept(cleaningVisitor);
        livingRoom.accept(cleaningVisitor);

        System.out.println("\nInspecting Rooms:");
        bedroom.accept(inspectionVisitor);
        kitchen.accept(inspectionVisitor);
        livingRoom.accept(inspectionVisitor);
    }
}
