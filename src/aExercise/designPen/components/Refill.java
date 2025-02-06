package aExercise.designPen.components;

public class Refill {
    private Ink ink;
    private double inkLevel; // ml
    private Nib nib; // Adding Nib to the refill

    public Refill(Ink ink, double inkLevel, Nib nib) {
        this.ink = ink;
        this.inkLevel = inkLevel;
        this.nib = nib;
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
}
