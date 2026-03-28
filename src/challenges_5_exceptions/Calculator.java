/* --- TASK ---
Write a method, which would ask a user to input numbers and when the user enters something that is not an int then it needs to print a message in the format "SUM = XX AVG = YY“, where XX represents the sum of all entered numbers of type int and YY represents the calculated average of all numbers of type double.
The method should not have any parameters and should not return anything (void), and it needs to keep reading int numbers from the keyboard until any letter is entered.
 */

package challenges_5_exceptions;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        // an instance of Calculator and calling the calculate method
        new Calculator().calculate();
    }

    public void calculate() {
        // variables to keep track of the sum and count of entered numbers
        int sum = 0;
        int count = 0;
        // using try-with-resources to ensure the Scanner is closed after use
        try (Scanner scanner = new Scanner(System.in)) {
            // reading integers from the user until a non-integer is entered
            while (true) {
                System.out.print("Enter an integer (or a non-integer to stop): ");
                // checking if the next input is an integer
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    sum += number;
                    count++;
                } else {
                    break;
                }
            }
        }

        // calculating the average and printing the results
        double average = count > 0 ? (double) sum / count : 0;
        System.out.printf("SUM = %d AVG = %.2f%n", sum, average);
    }
}
