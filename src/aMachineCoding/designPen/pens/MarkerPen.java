package aMachineCoding.designPen.pens;

import aMachineCoding.designPen.components.Ink;
import aMachineCoding.designPen.components.Nib;
import aMachineCoding.designPen.components.PenType;

// Concrete implementation of MarkerPen
public class MarkerPen extends Pen {
    private final Ink ink; // Ink of the marker
    private final Nib nib; // Nib size and type for the marker

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
