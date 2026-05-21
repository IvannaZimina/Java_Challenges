/*This challenge is designed to exercise your skills with methods on Arrays and ArrayLists, that are targets for lambda expressions.
First, create an array of String, which is populated with first names, in mixed case.
Include at least one name, which is spelled the same backwards, and forwards, like Anna.
Use Arrays.setAll, or List.replaceAll, to change the values in this array.
If you use List methods, you'll need a list backed by the array, so that changes get made to the initial array.
Using one of those two methods, perform the following functions on the elements in the array, with appropriate lambda expressions.  
Transform names to all uppercase.
Add a randomly generated middle initial and include a period.
Add a last name that is the reverse of the first name.
Print your array or the array elements, after each change, using the forEach method, at least once. 
Finally, create a new modifiable ArrayList from your names array, removing any names where the last name equals the first name. Use removeIf with a lambda expression to perform this last operation.
*/


// Uses lambdas to transform an array of names: uppercase, add middle initial, add reversed last name, filter
package challenges_21_lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdasInArrays {

	public static void main(String[] args) {
		// Create an array with mixed-case first names
		String[] names = { "Anna", "bob", "Charlie", "david", "Eve" };

		// Print helper
		java.util.function.Consumer<String[]> printer = arr -> Arrays.stream(arr).forEach(System.out::println);

		System.out.println("Original:");
		printer.accept(names);

		// 1) Transform names to uppercase using Arrays.setAll
		Arrays.setAll(names, i -> names[i].toUpperCase());
		System.out.println("After toUpperCase:");
		printer.accept(names);

		// 2) Add a random middle initial and period
		Random rnd = new Random(42);
		Arrays.setAll(names, i -> names[i] + " " + (char) ('A' + rnd.nextInt(26)) + ".");
		System.out.println("After adding middle initial:");
		printer.accept(names);

		// 3) Add a last name that is the reverse of the first name
		Arrays.setAll(names, i -> {
			String first = names[i].split(" ")[0];
			String last = new StringBuilder(first).reverse().toString();
			return names[i] + " " + last;
		});
		System.out.println("After adding reversed last name:");
		printer.accept(names);

		// 4) Create a modifiable ArrayList from the names array
		List<String> list = new ArrayList<>(Arrays.asList(names));

		// Remove any names where the last name equals the first name
		// Loop: removeIf will check each element and remove matching ones
		list.removeIf(s -> {
			String[] parts = s.split(" ");
			String first = parts[0];
			String last = parts[parts.length - 1];
			return first.equalsIgnoreCase(last);
		});

		System.out.println("After removing palindromic last names:");
		list.forEach(System.out::println);
	}

}
