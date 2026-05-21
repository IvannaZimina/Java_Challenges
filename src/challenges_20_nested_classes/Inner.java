/* 
Create in the class Meal another inner class, called Burger. This should be a specialized Item, and should also include a list of toppings, also Items.
Remember Items have a name, type, price, and method toString.
Allow a user to add toppings using the Meal class, which it should then delegate to its burger class.
Allow toppings to be added with a method that allows for a variable number of Strings to be entered, representing the toppings selected.
Allow toppings to be priced differently, some are free, some have an additional cost. Print the toppings out along with the burger information.
*/

// Implements Meal with inner Burger, toppings support and pricing demo
package challenges_20_nested_classes;
import java.util.ArrayList;
import java.util.List;

public class Inner {

	// Item class: name, type, price
	static class Item {
		String name;
		String type;
		double price;

		Item(String name, String type, double price) {
			this.name = name;
			this.type = type;
			this.price = price;
		}

		public String toString() {
			return String.format("%s (%s) - %.2f", name, type, price);
		}
	}

	// Meal contains a Burger inner class
	static class Meal {
		private Burger burger;

		Meal(String burgerName, double basePrice) {
			this.burger = new Burger(burgerName, basePrice);
		}

		// Allow adding toppings by names (varargs). Some toppings cost extra.
		public void addToppings(String... toppings) {
			// Delegate to burger
			burger.addToppings(toppings);
		}

		public void printMeal() {
			System.out.println("Burger: " + burger.name + " base price: " + burger.basePrice);
			System.out.println("Toppings:");
			for (Item t : burger.toppings) {
				System.out.println("- " + t);
			}
			System.out.printf("Total price: %.2f\n", burger.totalPrice());
		}

		// Inner class Burger: specialized Item with toppings
		class Burger {
			String name;
			double basePrice;
			List<Item> toppings = new ArrayList<>();

			Burger(String name, double basePrice) {
				this.name = name;
				this.basePrice = basePrice;
			}

			// Add toppings by names; some toppings have extra cost
			void addToppings(String... names) {
				for (String n : names) {
					// Some toppings are free (e.g., lettuce), others cost more
					double price = 0.0;
					String type = "topping";
					String lower = n.toLowerCase();
					if (lower.contains("cheese") || lower.contains("bacon")) {
						price = 0.75; // extra cost
					} else if (lower.contains("avocado")) {
						price = 1.25;
					} else {
						price = 0.0; // free
					}
					toppings.add(new Item(n, type, price));
				}
			}

			// Calculate total price (base + toppings prices)
			double totalPrice() {
				double sum = basePrice;
				for (Item t : toppings) {
					sum += t.price;
				}
				return sum;
			}
		}
	}

	// Simple demo main
	public static void main(String[] args) {
		Meal meal = new Meal("Classic Burger", 5.00);
		meal.addToppings("Lettuce", "Tomato", "Cheese", "Bacon");
		meal.printMeal();
	}

}
