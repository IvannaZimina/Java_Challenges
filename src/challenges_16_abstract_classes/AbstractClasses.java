// Store demo: abstract ProductForSale, OrderItem, concrete products, and receipt printing
package challenges_16_abstract_classes;

import java.util.ArrayList;
import java.util.List;

// Main class for the exercise. Acts as the Store with a main method.
public class AbstractClasses {

	// Main method: build products, add items to an order, and print receipt
	public static void main(String[] args) {
		// Create a list of products available in the store
		List<ProductForSale> products = new ArrayList<>();
		products.add(new BookProduct("Effective Java", 45.0, "Programming best practices"));
		products.add(new ElectronicProduct("Headphones", 79.99, "Over-ear, noise-cancelling"));
		products.add(new FoodProduct("Coffee Beans", 12.5, "500g, medium roast"));

		// Print product details for the store page
		System.out.println("Products for sale:");
		// Loop: show details for each product in the products list
		for (ProductForSale p : products) {
			p.showDetails();
			System.out.println();
		}

		// Create an order (list of OrderItem)
		List<OrderItem> order = new ArrayList<>();

		// Add items to the order using helper method
		addToOrder(order, products.get(0), 2); // 2 books
		addToOrder(order, products.get(1), 1); // 1 headphones
		addToOrder(order, products.get(2), 3); // 3 coffee

		// Print the receipt
		System.out.println("--- Receipt ---");
		printReceipt(order);
	}

	// Add an item to the order list
	// If quantity is zero or negative, do nothing
	private static void addToOrder(List<OrderItem> order, ProductForSale product, int quantity) {
		if (quantity <= 0) {
			return; // ignore invalid quantities
		}
		order.add(new OrderItem(quantity, product));
	}

	// Print ordered items and total price
	private static void printReceipt(List<OrderItem> order) {
		double total = 0.0;
		// Loop: print each ordered item and sum the total
		for (OrderItem item : order) {
			item.getProduct().printPricedItem(item.getQuantity());
			total += item.getProduct().getSalesPrice(item.getQuantity());
		}
		System.out.printf("Total: %.2f\n", total);
	}

}

// Abstract product class. Has type, price and description.
abstract class ProductForSale {
	protected String type;
	protected double price;
	protected String description;

	ProductForSale(String type, double price, String description) {
		this.type = type;
		this.price = price;
		this.description = description;
	}

	// Concrete method: calculate sales price for a quantity
	// Returns quantity * price
	public double getSalesPrice(int quantity) {
		return quantity * price;
	}

	// Concrete method: print a line item with quantity and line price
	public void printPricedItem(int quantity) {
		double linePrice = getSalesPrice(quantity);
		System.out.printf("%d x %s - %.2f\n", quantity, type + ": " + description, linePrice);
	}

	// Abstract method: show product details on a product page
	public abstract void showDetails();
}

// OrderItem type: holds quantity and the ProductForSale
class OrderItem {
	private int quantity;
	private ProductForSale product;

	OrderItem(int quantity, ProductForSale product) {
		this.quantity = quantity;
		this.product = product;
	}

	// Getter for quantity
	public int getQuantity() {
		return quantity;
	}

	// Getter for product
	public ProductForSale getProduct() {
		return product;
	}
}

// Concrete product: Book
class BookProduct extends ProductForSale {
	BookProduct(String title, double price, String description) {
		super("Book - " + title, price, description);
	}

	// Show details for this book product
	@Override
	public void showDetails() {
		System.out.println("Type: " + type);
		System.out.println("Description: " + description);
		System.out.printf("Price: %.2f\n", price);
	}
}

// Concrete product: Electronic
class ElectronicProduct extends ProductForSale {
	ElectronicProduct(String name, double price, String description) {
		super("Electronic - " + name, price, description);
	}

	// Show details for this electronic product
	@Override
	public void showDetails() {
		System.out.println("Type: " + type);
		System.out.println("Description: " + description);
		System.out.printf("Price: %.2f\n", price);
	}
}

// Concrete product: Food
class FoodProduct extends ProductForSale {
	FoodProduct(String name, double price, String description) {
		super("Food - " + name, price, description);
	}

	// Show details for this food product
	@Override
	public void showDetails() {
		System.out.println("Type: " + type);
		System.out.println("Description: " + description);
		System.out.printf("Price: %.2f\n", price);
	}
}


// ======= output =======
// Products for sale:
// Type: Book - Effective Java
// Description: Programming best practices
// Price: 45,00

// Type: Electronic - Headphones
// Description: Over-ear, noise-cancelling
// Price: 79,99

// Type: Food - Coffee Beans
// Description: 500g, medium roast
// Price: 12,50

// --- Receipt ---
// 2 x Book - Effective Java: Programming best practices - 90,00
// 1 x Electronic - Headphones: Over-ear, noise-cancelling - 79,99
// 3 x Food - Coffee Beans: 500g, medium roast - 37,50
// Total: 207,49