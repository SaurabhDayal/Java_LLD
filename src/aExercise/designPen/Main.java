package aExercise.designPen;

import aExercise.designPen.components.*;
import aExercise.designPen.pens.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        // ---- 1. Ballpoint Pen (Refillable)
        // Create Ink, Nib, and Refill for Ballpoint pen
        Ink ballpointInk = new Ink("Blue", PenType.BALL_POINT_PEN);
        Nib ballpointNib = new Nib(1.0, NibType.MEDIUM);
        Refill ballpointRefill = new Refill(ballpointInk, 2.0, ballpointNib);

        BallPointPen ballPen = new BallPointPen("Pilot", "Black", 1.5, ballpointRefill);
        System.out.println("Ball Pen Example:");
        ballPen.write("Writing with Pilot G2 Ballpoint pen.");
        ballPen.refill(new Refill(new Ink("Red", PenType.BALL_POINT_PEN), 2.5, ballpointNib)); // Refill Ballpoint pen with red ink and medium nib
        ballPen.write("Refilled with red ink!");
        System.out.println();

        // ---- 2. Gel Pen (Refillable)
        // Create Ink, Nib, and Refill for Gel pen
        Ink gelInk = new Ink("Blue", PenType.GEL_PEN);
        Nib gelNib = new Nib(0.5, NibType.FINE);
        Refill gelRefill = new Refill(gelInk, 1.5, gelNib);

        GelPen gelPen = new GelPen("Uni", "Blue", 2.0, gelRefill);
        System.out.println("Gel Pen Example:");
        gelPen.write("Writing with Uni-ball Gel pen.");
        gelPen.refill(new Refill(new Ink("Green", PenType.GEL_PEN), 1.8, gelNib)); // Refill Gel pen with green ink and fine nib
        gelPen.write("Refilled with green ink!");
        System.out.println();

        // ---- 3. Fountain Pen (Non-Refillable)
        // Create Ink and Nib for Fountain pen
        Ink fountainInk = new Ink("Green", PenType.FOUNTAIN_PEN);
        Nib fountainNib = new Nib(0.5, NibType.FINE);

        FountainPen fountainPen = new FountainPen("Parker", "Silver", 20.0, fountainInk, fountainNib);
        System.out.println("Fountain Pen Example:");
        fountainPen.write("Writing with Parker Fountain pen.");
        fountainPen.refillInk(new Ink("Blue", PenType.FOUNTAIN_PEN)); // Refill Fountain pen with different ink
        fountainPen.write("Refilled with blue ink!");
        fountainPen.changeNib(new Nib(1.0, NibType.MEDIUM)); // Change nib of Fountain pen
        fountainPen.write("Writing with medium nib now!");
        System.out.println();

        // ---- 4. Marker Pen (Non-Refillable)
        // Create Ink and Nib for Marker pen
        Ink markerInk = new Ink("Black", PenType.MARKER_PEN);
        Nib markerNib = new Nib(1.5, NibType.BROAD);

        MarkerPen markerPen = new MarkerPen("Sharpie", "Black", 2.5, markerInk, markerNib);
        System.out.println("Marker Pen Example:");
        markerPen.write("Writing with Sharpie Marker pen.");
        System.out.println();

        // ---- 5. Disposable Stick Pen (Non-Refillable)
        // Create Ink and Nib for Disposable Stick pen
        Ink stickPenInk = new Ink("Blue", PenType.BALL_POINT_PEN);
        Nib stickPenNib = new Nib(1.0, NibType.MEDIUM);

        DisposableStickPen stickPen = new DisposableStickPen("Bic", "Blue", 0.5, stickPenInk, stickPenNib);
        System.out.println("Disposable Stick Pen Example:");
        stickPen.write("Writing with Bic Disposable Stick pen.");
        System.out.println();
    }
}
