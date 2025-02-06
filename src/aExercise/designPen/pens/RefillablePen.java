package aExercise.designPen.pens;

import aExercise.designPen.components.PenType;
import aExercise.designPen.components.Refill;
import aExercise.designPen.interfaces.Refillable;

// Class for refillable pens (BallPen, GelPen, etc.)
abstract class RefillablePen extends Pen implements Refillable {
    protected Refill refill;

    public RefillablePen(String brand, String color, double price, PenType penType, Refill refill) {
        super(brand, color, price, penType); // Pass PenType enum to the constructor
        this.refill = refill;
    }

    public void refill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("Refill replaced with " + newRefill.getInk().getColor() + " ink.");
    }
}
