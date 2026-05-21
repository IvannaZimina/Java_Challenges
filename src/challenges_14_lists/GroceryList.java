// Implements an interactive grocery list: menu, add/remove, no duplicates, sorted print
package challenges_14_lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GroceryList {

	// Simple interactive grocery list program.
	// Menu options: 0 = quit, 1 = add item, 2 = remove item
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();

		while (true) {
			// Print menu
			System.out.println("Menu:");
			System.out.println("0 - Quit");
			System.out.println("1 - Add item");
			System.out.println("2 - Remove item");
			System.out.print("Enter choice: ");

			// Read menu choice (expect integer)
			String choiceLine = scanner.nextLine().trim();
			if (choiceLine.isEmpty()) {
				System.out.println();
				continue;
			}

			int choice;
			try {
				choice = Integer.parseInt(choiceLine);
			} catch (NumberFormatException e) {
				// If not an integer, tell the user and re-show menu
				System.out.println("Invalid choice. Please enter 0, 1 or 2.");
				System.out.println();
				continue;
			}

			if (choice == 0) {
				// Quit the program
				System.out.println("Goodbye!");
				break;
			} else if (choice == 1) {
				// Add item: ask for item name
				System.out.print("Enter item to add: ");
				String item = scanner.nextLine().trim();
				if (item.isEmpty()) {
					System.out.println("No item entered.");
				} else if (list.contains(item)) {
					// Do not allow duplicate items
					System.out.println("Item already in the list.");
				} else {
					list.add(item);
				}

				// Print sorted list after operation
				printSortedList(list);

			} else if (choice == 2) {
				// Remove item: ask for item name
				System.out.print("Enter item to remove: ");
				String item = scanner.nextLine().trim();
				if (item.isEmpty()) {
					System.out.println("No item entered.");
				} else if (!list.contains(item)) {
					System.out.println("Item not found in the list.");
				} else {
					list.remove(item);
				}

				// Print sorted list after operation
				printSortedList(list);

			} else {
				// Unknown menu option
				System.out.println("Invalid choice. Please enter 0, 1 or 2.");
			}

			System.out.println();
		}

		scanner.close();
	}

	// Helper method: print the grocery list sorted alphabetically
	private static void printSortedList(ArrayList<String> list) {
		// Make a copy so we don't change insertion order unexpectedly
		ArrayList<String> copy = new ArrayList<>(list);
		Collections.sort(copy, String.CASE_INSENSITIVE_ORDER);

		System.out.println("Grocery list:");
		if (copy.isEmpty()) {
			System.out.println("(empty)");
			return;
		}

		for (String s : copy) {
			System.out.println("- " + s);
		}
	}

}

// ====== output ======
// Menu:
// 0 - Quit
// 1 - Add item
// 2 - Remove item
// Enter choice: 1
// Enter item to add: Grape
// Grocery list:
// - Grape

// Menu:
// 0 - Quit
// 1 - Add item
// 2 - Remove item
// Enter choice: 1
// Enter item to add: Apple
// Grocery list:
// - Apple
// - Grape

// Menu:
// 0 - Quit
// 1 - Add item
// 2 - Remove item
// Enter choice: 0
// Goodbye!