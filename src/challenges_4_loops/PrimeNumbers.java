/* --- TASK ---
Write a method which takes in two integer numbers and returns how many prime numbers are in a range between those two numbers.
*/

package challenges_4_loops;

import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        // scanner for reading numbers from keyboard.
        try (Scanner scanner = new Scanner(System.in)) {
            // for both numbers: ask the numbers and check the result if the user input is an integer number, if not, show error and exit.

            System.out.print("Enter first integer: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer number.");
                return;
            }
            int firstNumber = scanner.nextInt();

            System.out.print("Enter second integer: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer number.");
                return;
            }
            int secondNumber = scanner.nextInt();

            // Count prime numbers in the given range.
            int result = countPrimesInRange(firstNumber, secondNumber);

            // Show the final answer.
            System.out.println("Prime numbers in range: " + result);
        }
    }

    // This method counts how many prime numbers are in the range between two given numbers.
    public static int countPrimesInRange(int firstNumber, int secondNumber) {
        // These lines normalize the range when numbers are given in reverse order.
        int start = Math.min(firstNumber, secondNumber);
        int end = Math.max(firstNumber, secondNumber);

        // variable stores how many prime numbers were found.
        int count = 0;

        // checking every number inside the range for being prime and counting them.
        for (int number = start; number <= end; number++) {
            if (isPrime(number)) {
                count++;
            }
        }

        // This return gives the total amount of prime numbers in the range.
        return count;
    }

    // This method checks if a given number is prime.
    public static boolean isPrime(int number) {
        // numbers less than 2 are not prime.
        if (number < 2) {
            return false;
        }

        // check if the number is divisible by any integer
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            // If the number is divisible by any integer, it is not prime.
            if (number % divisor == 0) {
                return false;
            }
        }

        // If no divisor was found, the number is prime.
        return true;
    }

}
