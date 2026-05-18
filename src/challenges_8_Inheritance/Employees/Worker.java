package challenges_8_Inheritance.Employees;

import java.time.Year;

// from task: Create the `Worker` class
// Base class that holds common person information used by Employee classes.
public class Worker {
    // worker's full name
    protected String name;
    // birth year used to calculate age
    protected int birthYear;

    // Constructor to initialize a Worker with a name and birth year
    public Worker(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    // Returns age based on current year and birthYear
    public int getAge() {
        return Year.now().getValue() - birthYear;
    }

    // Simple readable representation of a worker: name and age
    @Override
    public String toString() {
        return name + " (age=" + getAge() + ")";
    }
}
