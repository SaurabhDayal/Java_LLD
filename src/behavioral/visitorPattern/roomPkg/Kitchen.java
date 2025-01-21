package behavioral.visitorPattern.roomPkg;

import behavioral.visitorPattern.visitorPkg.RoomVisitor;

// Concrete Element
public class Kitchen implements Room {
    private String name;

    public Kitchen(String name) {
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