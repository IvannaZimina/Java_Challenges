/* --- TASK ---
In this challenge, you'll read 5 valid numbers from the console entered by the user and print the sum of those five numbers.
You need to check that the numbers entered are valid integers. If they are not, print out the message "Invalid number" to the console, but continue looping until you do have 5 valid numbers.
Before the user enters each number, prompt them with the message, "Enter number #x:", where x represents the count 1, 2, 3, etc.
As an example, the first message would look something like, "Enter number #1:", the next, "Enter number #2:", and so on.
*/

package challenges_5_exceptions;

import java.util.Scanner;

public class InvalidNumbers {
    public static void main(String[] args) {
        // an instance of InvalidNumbers and calling the readAndSumNumbers method
        new InvalidNumbers().readAndSumNumbers();
    }

    public void readAndSumNumbers() {
        // variable to keep track of the sum of valid numbers
        int sum = 0;

        // using try-with-resources to ensure the Scanner is closed after use
        try (Scanner scanner = new Scanner(System.in)) {
            
            // loop until we have read 5 valid numbers
            for (int i = 1; i <= 5; ) {
                System.out.print("Enter number #" + i + ": ");

                // checking if the next input is an integer
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    sum += number;
                    i++; // increment the count of valid numbers
                } else {
                    System.out.println("Invalid number");
                    scanner.next(); // consume the invalid input
                }
            }
        }

        // printing the final sum of valid numbers
        System.out.println("The sum of the entered numbers is: " + sum);
    }
}
