package behavioral.visitorPattern.roomPkg;

import behavioral.visitorPattern.visitorPkg.RoomVisitor;

public class Bedroom implements Room {
    private String name;

    public Bedroom(String name) {
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
