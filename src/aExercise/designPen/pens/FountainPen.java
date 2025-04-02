package aExercise.designPen.pens;

import aExercise.designPen.components.Ink;
import aExercise.designPen.components.Nib;
import aExercise.designPen.components.PenType;
import aExercise.designPen.interfaces.InkRefillable;

// Concrete implementation of FountainPen
public class FountainPen extends Pen implements InkRefillable {
    private Ink ink;  // Direct ink storage
    private Nib nib;  // Nib (tip) of the pen

    public FountainPen(String brand, String color, double price, Ink ink, Nib nib) {
        super(brand, color, price, PenType.FOUNTAIN_PEN);
        this.ink = ink;
        this.nib = nib;
    }

    @Override
    public void write(String text) {
        System.out.println("Writing with Fountain Pen (" + nib.getSize() + "mm, " + nib.getType() + " nib) using " + ink.getColor() + " ink: " + text);
    }

    @Override
    public void refillInk(Ink newInk) {
        this.ink = newInk;
        System.out.println("Fountain Pen refilled with " + newInk.getColor() + " ink.");
    }

    public void changeNib(Nib newNib) {
        this.nib = newNib;
        System.out.println("Fountain Pen nib changed to " + newNib.getSize() + "mm " + newNib.getType() + " nib.");
    }
}
