/* --- TASK ---
Create an endless loop which:
Prompts the user to enter a number or any character to quit.
Validates if the user-entered data really is a number.
You can choose either an integer or double validation method.
If the user-entered data is not a number, quit the loop.
Keep track of the minimum number entered.
Keep track of the maximum number entered.
If the user has previously entered a set of numbers (or even just one),
display the minimum and maximum number that the user entered.
*/

package challenges_5_exceptions;

import java.util.Scanner;

public class MinimumMaximumNumbers {
    public static void main(String[] args) {
        // an instance of MinimumMaximumNumbers and calling the findMinMax method
        new MinimumMaximumNumbers().findMinMax();
    }

    public void findMinMax() {
        // variables to keep track of the minimum and maximum numbers entered
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // using try-with-resources to ensure the Scanner is closed after use
        try (Scanner scanner = new Scanner(System.in)) {

            // reading numbers from the user until a non-number is entered
            while (true) {
                System.out.print("Enter a number (or a non-number to stop): ");

                // checking if the next input is a double
                if (scanner.hasNextDouble()) {
                    double number = scanner.nextDouble();
                    
                    // updating the minimum and maximum values
                    if (number < min) {
                        min = number;
                    }
                    if (number > max) {
                        max = number;
                    }
                } else {
                    break;
                }
            }
        }

        // checking if any numbers were entered and printing the results
        if (min == Double.MAX_VALUE && max == Double.MIN_VALUE) {
            System.out.println("No numbers were entered.");
        } else {
            System.out.printf("Minimum number entered: %.2f%n", min);
            System.out.printf("Maximum number entered: %.2f%n", max);
        }
    }
}
