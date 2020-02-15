package restservice;

public class Car {

    private final long id;
    private final String brand;
    private final String model;
    private final int horsePower;
    private final String color;

    public Car(long id, String brand, String model, int horsePower, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getColor() {
        return color;
    }
}
