package challenges_08_Inheritance.Employees;

// Main class with demonstration of the Worker/Employee hierarchy
// from task: Create a `main` method that will create a `SalariedEmployee` and a `HourlyEmployee`,
// and call the methods `getAge`, `collectPay`, and the subclass methods `retire` and `getDoublePay`.
public class Employees {
	public static void main(String[] args) {
		// create a salaried employee (Liisi) and an hourly employee (Priit)
		SalariedEmployee liisi = new SalariedEmployee("Liisi", 1980, 1001, 72000);
		HourlyEmployee priit = new HourlyEmployee("Priit", 1992, 1002, 18.5, 9);

		// Print objects using Employee.toString()
		System.out.println("-- Objects --");
		System.out.println(liisi.toString());
		System.out.println(priit.toString());

		// getAge() is defined on Worker and used by both subclasses
		System.out.println("\n-- getAge() --");
		System.out.println("Liisi age: " + liisi.getAge());
		System.out.println("Priit age: " + priit.getAge());

		// collectPay() is overridden in each subclass
		System.out.println("\n-- collectPay() before retire/bonus --");
		System.out.println("Liisi pay: " + liisi.collectPay());
		System.out.println("Priit pay: " + priit.collectPay());

		// retire Liisi and show changed pay behavior
		System.out.println("\n-- Salaried retire and collectPay() after retire --");
		liisi.retire();
		System.out.println("Liisi pay after retire: " + liisi.collectPay());

		// Hourly employee specific method getDoublePay()
		System.out.println("\n-- Hourly getDoublePay() --");
		System.out.println("Priit double pay (overtime): " + priit.getDoublePay());
	}
}
