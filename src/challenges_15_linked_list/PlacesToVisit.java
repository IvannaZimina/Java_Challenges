// Implements an ordered itinerary using LinkedList and ListIterator with interactive menu
package challenges_15_linked_list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class PlacesToVisit {

	// Simple data type to hold a place name and distance from start
	private static class Place {
		String name;
		int distance; // distance from Tallinn in km (integer)

		Place(String name, int distance) {
			this.name = name;
			this.distance = distance;
		}

		// Print as "Name (distance km)" when needed
		public String toString() {
			return name + " (" + distance + " km)";
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Create linked list of places, ordered by distance from Tallinn
		LinkedList<Place> itinerary = new LinkedList<>();

		// Add initial places. Tallinn must be first (distance 0).
		addPlaceOrdered(itinerary, new Place("Tallinn", 0));
		addPlaceOrdered(itinerary, new Place("Tartu", 186));
		addPlaceOrdered(itinerary, new Place("Parnu", 128));
		addPlaceOrdered(itinerary, new Place("Narva", 214));
		addPlaceOrdered(itinerary, new Place("Haapsalu", 100));

		// Start interactive visiting using a ListIterator
		ListIterator<Place> iterator = itinerary.listIterator();
		boolean quit = false;
		boolean goingForward = true; // track direction for iterator adjustments

		printMenu();

		// Main loop: read user commands and act until the user quits
		while (!quit) {
			System.out.print("Enter action (1 to show menu): ");
			String line = scanner.nextLine().trim();
			if (line.isEmpty()) {
				continue;
			}

			int action;
			try {
				action = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Enter a number from the menu.");
				continue;
			}

			switch (action) {
				case 0:
					// Quit program
					System.out.println("Exiting itinerary.");
					quit = true;
					break;

				case 1:
					// Show menu
					printMenu();
					break;

				case 2:
					// Visit next place
					if (!goingForward) {
						// We changed direction: the list iterator cursor is between
						// elements. Move forward once to align the cursor before next()
						if (iterator.hasNext()) {
							iterator.next();
						}
						goingForward = true;
					}

					if (iterator.hasNext()) {
						Place next = iterator.next();
						System.out.println("Now visiting: " + next);
					} else {
						System.out.println("Reached the end of the itinerary.");
					}
					break;

				case 3:
					// Visit previous place
					if (goingForward) {
						// We changed direction: the iterator cursor is between elements.
						// Move back once to align the cursor before previous()
						if (iterator.hasPrevious()) {
							iterator.previous();
						}
						goingForward = false;
					}

					if (iterator.hasPrevious()) {
						Place prev = iterator.previous();
						System.out.println("Now visiting: " + prev);
					} else {
						System.out.println("We are at the start of the itinerary.");
					}
					break;

				case 4:
					// Add a new place (name + distance)
					System.out.print("Enter place name: ");
					String name = scanner.nextLine().trim();
					if (name.isEmpty()) {
						System.out.println("No name entered.");
						break;
					}
					System.out.print("Enter distance from Tallinn (integer km): ");
					String distLine = scanner.nextLine().trim();
					int dist;
					try {
						dist = Integer.parseInt(distLine);
					} catch (NumberFormatException e) {
						System.out.println("Invalid distance. Must be an integer.");
						break;
					}

					Place newPlace = new Place(name, dist);
					boolean added = addPlaceOrdered(itinerary, newPlace);
					if (added) {
						System.out.println("Place added: " + newPlace);
						// Reset iterator to start after modification to keep behavior simple
						iterator = itinerary.listIterator();
						goingForward = true;
					} else {
						System.out.println("Place not added (duplicate name).");
					}
					break;

				case 5:
					// Print full itinerary
					printItinerary(itinerary);
					break;

				default:
					System.out.println("Unknown action. Enter 1 to see menu.");
					break;
			}
		}

		scanner.close();
	}

	// Helper: add place into linked list maintaining ascending distance order
	// Returns true if added, false if duplicate name found
	private static boolean addPlaceOrdered(LinkedList<Place> list, Place place) {
		// Loop: check existing places for the same name (case-insensitive)
		// If a duplicate name is found, do not add the new place
		for (Place p : list) {
			if (p.name.equalsIgnoreCase(place.name)) {
				return false;
			}
		}

		// Loop: walk through the list to find where the new place's distance
		// is smaller than the current element. Insert before that element.
		ListIterator<Place> it = list.listIterator();
		while (it.hasNext()) {
			Place current = it.next();
			if (place.distance < current.distance) {
				// step back and insert before current
				it.previous();
				it.add(place);
				return true;
			}
		}

		// If we reach here, place has the largest distance -> add at end
		list.add(place);
		return true;
	}

	// Helper: print full itinerary in order
	private static void printItinerary(LinkedList<Place> list) {
		System.out.println("Itinerary:");
		if (list.isEmpty()) {
			System.out.println("(no places)");
			return;
		}
		// Loop: iterate over each place and print it on its own line
		Iterator<Place> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("- " + it.next());
		}
	}

	// Print menu choices (simple English comments based on assignment)
	private static void printMenu() {
		System.out.println("Menu options:");
		System.out.println("0 - Quit");
		System.out.println("1 - Show menu options");
		System.out.println("2 - Visit next place (move forward)");
		System.out.println("3 - Visit previous place (move backward)");
		System.out.println("4 - Add a new place (name and distance)");
		System.out.println("5 - Print full itinerary");
	}

}

// ====== output example ======
// Menu options:
// 0 - Quit
// 1 - Show menu options
// 2 - Visit next place (move forward)
// 3 - Visit previous place (move backward)
// 4 - Add a new place (name and distance)
// 5 - Print full itinerary
// Enter action (1 to show menu): 2
// Now visiting: Tallinn (0 km)
// Enter action (1 to show menu): Paide
// Invalid input. Enter a number from the menu.
// Enter action (1 to show menu): 3
// We are at the start of the itinerary.
// Enter action (1 to show menu): 4
// Enter place name: Paide
// Enter distance from Tallinn (integer km): 40
// Place added: Paide (40 km)
// Enter action (1 to show menu): 0
// Exiting itinerary.
