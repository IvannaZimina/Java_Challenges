package challenges_11_polymorphism;

public class HybridCar extends Car {
    private String fuelType;
    private int fuelLevel;
    private int batteryLevel;

    public HybridCar(String description, String fuelType, int fuelLevel, int batteryLevel) {
        super(description);
        this.fuelType = fuelType;
        this.fuelLevel = fuelLevel;
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void startEngine() {
        System.out.println("Initializing hybrid systems for " + getClass().getSimpleName() + " (" + description + ")");
    }

    @Override
    protected void runEngine() {
        System.out.println("Hybrid running: " + batteryLevel + "% battery + " + fuelLevel + "% " + fuelType);
    }
}
