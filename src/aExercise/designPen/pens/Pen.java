package aExercise.designPen.pens;

import aExercise.designPen.components.PenType;

// Base class for all types of pens
abstract class Pen {
    protected String brand;
    protected String color;
    protected double price;
    protected PenType penType;  // Added Enum Field

    public Pen(String brand, String color, double price, PenType penType) {
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.penType = penType;
    }

    public abstract void write(String text);
}

