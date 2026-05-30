package challenges_08_Inheritance.Employees;

// from task: Create the `Employee` class that extends `Worker`
// Employee adds employee-specific data and behavior on top of Worker.
public class Employee extends Worker {
    // unique employee identifier
    protected int employeeId;

    // Initialize Employee with name, birth year and id
    public Employee(String name, int birthYear, int employeeId) {
        super(name, birthYear);
        this.employeeId = employeeId;
    }

    // Include worker information and employee id in string output
    @Override
    public String toString() {
        return super.toString() + " [id=" + employeeId + "]";
    }

    // Default collectPay at Employee level (may be overridden by subclasses)
    public double collectPay() {
        return 0.0;
    }
}
