package challenges_8_Inheritance.Employees;

// from task: Create the `HourlyEmployee` class
// HourlyEmployee is paid by hours worked and hourly rate.
public class HourlyEmployee extends Employee {
    // hourly pay rate
    private double hourlyRate;
    // hours worked in the pay period
    private double hoursWorked;

    // Initialize with hourly rate and hours worked
    public HourlyEmployee(String name, int birthYear, int employeeId, double hourlyRate, double hoursWorked) {
        super(name, birthYear, employeeId);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // collectPay overridden: simple hours * rate
    @Override
    public double collectPay() {
        return hourlyRate * hoursWorked;
    }

    // getDoublePay: from task: "An hourly employee may also get double pay if they work over a certain number of hours."
    // Here we treat hours over 8 as eligible for double pay of the total amount.
    public double getDoublePay() {
        double normal = hourlyRate * hoursWorked;
        if (hoursWorked > 8) {
            return normal * 2;
        }
        return normal;
    }
}

