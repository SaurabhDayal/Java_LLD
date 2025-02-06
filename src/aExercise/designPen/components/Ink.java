package aExercise.designPen.components;

public class Ink {
    private String color;
    private PenType type; // Gel, Ballpoint, Fountain, etc.

    public Ink(String color, PenType type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public PenType getType() {
        return type;
    }
}
