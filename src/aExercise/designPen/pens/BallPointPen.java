package aExercise.designPen.pens;

import aExercise.designPen.components.PenType;
import aExercise.designPen.components.Refill;

// Concrete implementation of BallPointPen
public class BallPointPen extends RefillablePen {
    public BallPointPen(String brand, String color, double price, Refill refill) {
        super(brand, color, price, PenType.BALL_POINT_PEN, refill);
    }

    @Override
    public void write(String text) {
        System.out.println("Writing with BallPoint Pen: " + text);
    }
}
