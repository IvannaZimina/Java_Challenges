package challenges_11_polymorphism;

public class ElectricCar extends Car {
    private int batteryLevel; // percentage

    public ElectricCar(String description, int batteryLevel) {
        super(description);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void startEngine() {
        System.out.println("Powering on electric motors for " + getClass().getSimpleName() + " (" + description + ")");
    }

    @Override
    protected void runEngine() {
        System.out.println("Electric motor running with " + batteryLevel + "% battery");
    }
}
