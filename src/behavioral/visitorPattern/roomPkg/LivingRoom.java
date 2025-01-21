package behavioral.visitorPattern.roomPkg;

import behavioral.visitorPattern.visitorPkg.RoomVisitor;

// Concrete Element
public class LivingRoom implements Room {
    private String name;

    public LivingRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);  // Accepting a visitor
    }
}