package challenges_11_polymorphism;

public class FuelPoweredCar extends Car {
    private String fuelType;
    private int fuelLevel; // percentage

    public FuelPoweredCar(String description, String fuelType, int fuelLevel) {
        super(description);
        this.fuelType = fuelType;
        this.fuelLevel = fuelLevel;
    }

    @Override
    public void startEngine() {
        System.out.println("Turning key to start " + getClass().getSimpleName() + " (" + description + ")");
    }

    @Override
    protected void runEngine() {
        System.out.println("Fuel engine running on " + fuelType + " with " + fuelLevel + "% fuel");
    }
}
