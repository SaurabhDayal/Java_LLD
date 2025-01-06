package creational.prototypePattern.prototypePkg;


public class Car extends Vehicle {

    private final int topSpeed;

    public Car(String brand, String model, String color, int topSpeed) {
        super(brand, model, color);
        this.topSpeed = topSpeed;
    }

    private Car(Car car) {
        super(car);
        this.topSpeed = car.topSpeed;
    }

    @Override
    public Car clone() {
        return new Car(this);
    }

    @Override
    public String toString() {
        return "Car{" +
                "Brand='" + getBrand() + '\'' +
                ", Model='" + getModel() + '\'' +
                ", Color='" + getColor() + '\'' +
                ", TopSpeed=" + topSpeed +
                '}';
    }

}
