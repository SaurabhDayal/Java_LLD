package aExercise.designPen.components;

// Class for Tip/Nib representation
public class Nib {
    private double size; // Tip size in mm
    private String type; // Fine, Medium, Broad

    public Nib(double size, String type) {
        this.size = size;
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}