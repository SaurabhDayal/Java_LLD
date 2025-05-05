package aMachineCoding.designPen.components;

// Class for Tip/Nib representation
public class Nib {
    private double size; // Tip size in mm
    private NibType type; // Fine, Medium, Broad (using Enum)

    public Nib(double size, NibType type) {
        this.size = size;
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public NibType getType() {
        return type;
    }
}
