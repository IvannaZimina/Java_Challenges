// Demonstrates method references and a list of UnaryOperator transforms applied to a names array
package challenges_22_method_references;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class MethodReferences {

	public static void main(String[] args) {
		String[] names = { "Anna", "bob", "Charlie", "david", "Eve" };

		// Create a list of transformations (UnaryOperator<String>)
		List<UnaryOperator<String>> ops = List.of(
				// method reference toUpperCase
				String::toUpperCase,
				// add random middle initial using lambda
				s -> s + " " + "M.",
				// add reversed last name using a method reference to helper
				MethodReferences::addReversedLastName,
				// custom transform: append length
				s -> s + " (len=" + s.length() + ")"
		);

		// Apply each function to each name and mutate original array
		applyTransforms(names, ops);

		// Print final array
		Arrays.stream(names).forEach(System.out::println);
	}

	// Apply all transforms in order to each element of the array
	private static void applyTransforms(String[] names, List<UnaryOperator<String>> ops) {
		// Loop: for each index, apply all operators
		for (int i = 0; i < names.length; i++) {
			String val = names[i];
			for (UnaryOperator<String> op : ops) {
				val = op.apply(val);
			}
			names[i] = val; // write back to original array
		}
	}

	// Helper: add reversed last name to the string
	private static String addReversedLastName(String s) {
		String first = s.split(" ")[0];
		String last = new StringBuilder(first).reverse().toString();
		return s + " " + last;
	}
}

// ===== output =====
// ANNA M. ANNA (len=12)
// BOB M. BOB (len=10)
// CHARLIE M. EILRAHC (len=18)
// DAVID M. DIVAD (len=14)
// EVE M. EVE (len=10)
