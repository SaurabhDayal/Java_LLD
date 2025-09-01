package aMachineCoding.designPen.pens;

import aMachineCoding.designPen.components.Ink;
import aMachineCoding.designPen.components.Nib;
import aMachineCoding.designPen.components.PenType;

// Concrete implementation of DisposableStickPen with Ink and Nib
public class DisposableStickPen extends Pen {
    private final Ink ink; // Disposable pen ink
    private final Nib nib; // Disposable pen nib

    public DisposableStickPen(String brand, String color, double price, Ink ink, Nib nib) {
        super(brand, color, price, PenType.DISPOSABLE_STICK_PEN);
        this.ink = ink;
        this.nib = nib;
    }

    @Override
    public void write(String text) {
        System.out.println("Writing with Disposable Stick Pen (" + nib.getSize() + "mm, " + nib.getType() + " nib) using " + ink.getColor() + " ink: " + text);
    }

    // Since it's disposable, no refill method
}
