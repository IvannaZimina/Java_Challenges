package challenges_11_polymorphism;

public class Car {
    protected String description;

    public Car(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void startEngine() {
        System.out.println("Starting generic car engine for " + description);
    }

    public void drive() {
        System.out.println("Driving: " + description);
        runEngine();
    }

    protected void runEngine() {
        System.out.println("Running generic engine for " + description);
    }
}
