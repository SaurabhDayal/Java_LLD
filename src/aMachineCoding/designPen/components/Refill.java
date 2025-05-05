package aMachineCoding.designPen.components;

public class Refill {
    private Ink ink;
    private double inkLevel; // ml
    private Nib nib; // Adding Nib to the refill
    private PenType type; // Gel, Ballpoint etc.

    public Refill(Ink ink, double inkLevel, Nib nib, PenType type) {
        this.ink = ink;
        this.inkLevel = inkLevel;
        this.nib = nib;
        this.type = type;
    }

    public Ink getInk() {
        return ink;
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public Nib getNib() {
        return nib;
    }

    public PenType getType() {
        return type;
    }
}
