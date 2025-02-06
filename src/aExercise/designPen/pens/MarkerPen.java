package aExercise.designPen.pens;

import aExercise.designPen.components.Ink;
import aExercise.designPen.components.Nib;
import aExercise.designPen.components.PenType;

// Concrete implementation of MarkerPen
public class MarkerPen extends Pen {
    private Ink ink; // Ink of the marker
    private Nib nib; // Nib size and type for the marker

    // Constructor initializing the ink and nib
    public MarkerPen(String brand, String color, double price, Ink ink, Nib nib) {
        super(brand, color, price, PenType.MARKER_PEN);
        this.ink = ink;
        this.nib = nib;
    }

    @Override
    public void write(String text) {
        System.out.println("Writing with Marker Pen (" + nib.getSize() + "mm " + nib.getType() + " nib) using " + ink.getColor() + " ink: " + text);
    }

    // Getter methods for ink and nib
    public Ink getInk() {
        return ink;
    }

    public Nib getNib() {
        return nib;
    }
}
