/*
Assignment notes (from task):
- "addWater() will set the Coffee Maker's hasWorkToDo field to true."  
- "pourMilk() will set the Refrigerator's hasWorkToDo to true."  
- "loadDishwasher() will set the hasWorkToDo flag to true for that appliance."  
- Direct execution: call Refrigerator.orderFood(), DishWasher.doDishes(), CoffeeMaker.brewCoffee().
- Delegation: SmartKitchen.doKitchenWork() delegates to appliances' performWork().
*/

package challenges_10_composition;

// Smart kitchen controller and appliance implementations
// Simple, clear comments explain what each class and method does.
public class SmatrKitchen {

	// The kitchen has one refrigerator, one dishwasher and one coffee maker
	private Refrigerator refrigerator;
	private DishWasher dishwasher;
	private CoffeeMaker coffeeMaker;

	// Constructor: create each appliance when kitchen is created
	public SmatrKitchen() {
		this.refrigerator = new Refrigerator();
		this.dishwasher = new DishWasher();
		this.coffeeMaker = new CoffeeMaker();
	}

	// Mark coffee maker as needing work (e.g. add water so it will brew)
	// from task: "addWater() will set the Coffee Maker's hasWorkToDo field to true." 
	public void addWater() {
		this.coffeeMaker.setHasWorkToDo(true);
	}

	// Mark refrigerator as needing work (e.g. it should order food)
	// from task: "pourMilk() will set the Refrigerator's hasWorkToDo to true." 
	public void pourMilk() {
		this.refrigerator.setHasWorkToDo(true);
	}

	// Mark dishwasher as needing work (it should run)
	// from task: "loadDishwasher() will set the hasWorkToDo flag to true for that appliance." 
	public void loadDishwasher() {
		this.dishwasher.setHasWorkToDo(true);
	}

	// Convenience: set all three appliance states at once
	// from task (alternate): "setKitchenState" takes three booleans to set each appliance accordingly. 
	public void setKitchenState(boolean fridgeWork, boolean dishwasherWork, boolean coffeeWork) {
		this.refrigerator.setHasWorkToDo(fridgeWork);
		this.dishwasher.setHasWorkToDo(dishwasherWork);
		this.coffeeMaker.setHasWorkToDo(coffeeWork);
	}

	// Direct approach: get each appliance and call its specific action
	// from task: call Refrigerator.orderFood(), DishWasher.doDishes(), CoffeeMaker.brewCoffee() directly. 
	public void doKitchenWorkDirect() {
		getRefrigerator().orderFood();
		getDishWasher().doDishes();
		getCoffeeMaker().brewCoffee();
	}

	// Delegation approach: tell each appliance to perform its own work
	// from task: "doKitchenWork() which delegates the work to any of its appliances." 
	public void doKitchenWork() {
		this.refrigerator.performWork();
		this.dishwasher.performWork();
		this.coffeeMaker.performWork();
	}

	// Simple getters to access appliances from outside if needed
	public Refrigerator getRefrigerator() {
		return this.refrigerator;
	}

	public DishWasher getDishWasher() {
		return this.dishwasher;
	}

	public CoffeeMaker getCoffeeMaker() {
		return this.coffeeMaker;
	}

	// Demo runner to show how both direct and delegated execution work
	public static void main(String[] args) {
		SmatrKitchen kitchen = new SmatrKitchen();

		System.out.println("--- Demo: Direct Execution ---");
		// Set work flags using the kitchen helper methods
		kitchen.addWater();      // coffee maker will brew
		kitchen.pourMilk();      // refrigerator will order food
		kitchen.loadDishwasher(); // dishwasher will run

		// Direct: call specific appliance methods via getters
		kitchen.doKitchenWorkDirect();

		System.out.println();
		System.out.println("--- Demo: Delegation Execution ---");

		// Set states again for delegation demo
		kitchen.setKitchenState(true, true, true);

		// Delegation: kitchen tells appliances to perform their own work
		kitchen.doKitchenWork();
	}
}

// Refrigerator appliance. Tracks whether it has work to do.
class Refrigerator {
	// Flag: true means the refrigerator has work (order food)
	private boolean hasWorkToDo = false;

	// Setter to mark if there is work for this appliance
	public void setHasWorkToDo(boolean hasWork) {
		this.hasWorkToDo = hasWork;
	}

	// If there is work, order food and clear the flag
	public void orderFood() {
		if (this.hasWorkToDo) {
			System.out.println("Refrigerator: ordering food...");
			this.hasWorkToDo = false;
		}
	}

	// Delegation helper: perform the work this appliance needs
	public void performWork() {
		orderFood();
	}
}

// Dishwasher appliance. Tracks whether it has work to do.
class DishWasher {
	// Flag: true means dishwasher should run
	private boolean hasWorkToDo = false;

	// Setter to mark work
	public void setHasWorkToDo(boolean hasWork) {
		this.hasWorkToDo = hasWork;
	}

	// If there is work, run the dishwasher and clear the flag
	public void doDishes() {
		if (this.hasWorkToDo) {
			System.out.println("DishWasher: doing the dishes...");
			this.hasWorkToDo = false;
		}
	}

	// Delegation helper
	public void performWork() {
		doDishes();
	}
}

// CoffeeMaker appliance. Tracks whether it has work to do.
class CoffeeMaker {
	// Flag: true means coffee maker should brew
	private boolean hasWorkToDo = false;

	// Setter to mark work
	public void setHasWorkToDo(boolean hasWork) {
		this.hasWorkToDo = hasWork;
	}

	// If there is work, brew coffee and clear the flag
	public void brewCoffee() {
		if (this.hasWorkToDo) {
			System.out.println("CoffeeMaker: brewing coffee...");
			this.hasWorkToDo = false;
		}
	}

	// Delegation helper
	public void performWork() {
		brewCoffee();
	}
}
