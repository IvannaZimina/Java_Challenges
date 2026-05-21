// Demonstrates a local class for calculated employee fields and an anonymous comparator
package challenges_20_nested_classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Demonstrate local class and anonymous comparator
public class LocalAnonymous {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", LocalDate.of(2015, 5, 10)));
        employees.add(new Employee("Jane", "Smith", LocalDate.of(2010, 3, 1)));
        employees.add(new Employee("Alice", "Brown", LocalDate.of(2020, 7, 20)));

        processEmployees(employees);
    }

    // Method that creates a local class and returns processed list
    public static void processEmployees(List<Employee> employees) {
        // Local class to hold calculated fields: fullName and yearsWorked
        class LocalEmployee {
            String fullName;
            int yearsWorked;

            LocalEmployee(Employee e) {
                // Use record accessor methods generated for Employee
                this.fullName = e.firstName() + " " + e.lastName();
                this.yearsWorked = yearsBetween(e.hireDate(), LocalDate.now());
            }

            @Override
            public String toString() {
                return String.format("%s - %d years", fullName, yearsWorked);
            }
        }

        List<LocalEmployee> list = new ArrayList<>();
        // Loop: create LocalEmployee for each Employee
        for (Employee e : employees) {
            list.add(new LocalEmployee(e));
        }

        // Anonymous class comparator: sort by yearsWorked descending
        Collections.sort(list, new Comparator<LocalEmployee>() {
            @Override
            public int compare(LocalEmployee o1, LocalEmployee o2) {
                return Integer.compare(o2.yearsWorked, o1.yearsWorked);
            }
        });

        // Print sorted list
        for (LocalEmployee le : list) {
            System.out.println(le);
        }
    }

    // Years between two dates helper
    private static int yearsBetween(LocalDate from, LocalDate to) {
        return Period.between(from, to).getYears();
    }

}

// Employee record as required
record Employee(String firstName, String lastName, LocalDate hireDate) {}
