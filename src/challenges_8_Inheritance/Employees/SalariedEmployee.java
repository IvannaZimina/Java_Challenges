package challenges_8_Inheritance.Employees;

// from task: Create the `SalariedEmployee` class
// SalariedEmployee is paid based on a portion of annual salary.
public class SalariedEmployee extends Employee {
    // annual salary amount
    private double annualSalary;
    // whether the employee is retired
    private boolean retired = false;

    // Constructor with annual salary value
    public SalariedEmployee(String name, int birthYear, int employeeId, double annualSalary) {
        super(name, birthYear, employeeId);
        this.annualSalary = annualSalary;
    }

    // collectPay overridden: returns monthly pay derived from annual salary
    // from task: "A salaried employee is paid based on some percentage of his or her annual salary."
    // If retired, pay is reduced (task notes retirement may change pay).
    @Override
    public double collectPay() {
        double monthly = annualSalary / 12.0;
        return retired ? monthly * 0.6 : monthly;
    }

    // Mark this employee as retired. from task: add `retire()` method.
    public void retire() {
        retired = true;
    }
}
