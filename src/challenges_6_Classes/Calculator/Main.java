package challenges_6_Classes.Calculator;

import java.util.Scanner;

// for testing the class Calculator - scanner is used to read user input for the first and second numbers
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Read two numbers from the user to test all operations.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Input is closed.");
                return;
            }
            String firstInput = scanner.nextLine().trim().replace(',', '.');

            System.out.print("Enter second number: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Input is closed.");
                return;
            }
            String secondInput = scanner.nextLine().trim().replace(',', '.');

            // Attempt to parse the inputs and set the numbers in the calculator
            try {
                calculator.setFirstNumber(Double.parseDouble(firstInput));
                calculator.setSecondNumber(Double.parseDouble(secondInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
                return;
            }
        }

        // Display the results of the calculations
        System.out.println("add= " + calculator.getAdditionResult());
        System.out.println("subtract= " + calculator.getSubtractionResult());
        System.out.println("multiply= " + calculator.getMultiplicationResult());
        System.out.println("divide= " + calculator.getDivisionResult());
    }
}
