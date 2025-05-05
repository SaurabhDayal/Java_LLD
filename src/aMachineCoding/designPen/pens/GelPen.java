package aMachineCoding.designPen.pens;

import aMachineCoding.designPen.components.PenType;
import aMachineCoding.designPen.components.Refill;

// Concrete implementation of GelPen
public class GelPen extends RefillablePen {
    public GelPen(String brand, String color, double price, Refill refill) {
        super(brand, color, price, PenType.GEL_PEN, refill);
    }

    @Override
    public void write(String text) {
        System.out.println("Writing with Gel Pen: " + text);
    }
}
