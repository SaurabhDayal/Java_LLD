package aExercise.designPen.components;

public class Ink {
    private String color;
    private String type; // Gel, Ballpoint, Fountain, etc.

    public Ink(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
