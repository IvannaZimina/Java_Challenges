package challenges_1_operators;
public class operators {
    public static void main(String[] args) {
        // Step 1: create a double variable with a value of 20.00.
        double first = 20.00;

        // Step 2: create a second variable of type double with a value 80.00.
        double second = 80.00;

        // Step 3: add both numbers together, then multiply by 100.00.
        double total = (first + second) * 100.00;
        System.out.println("total = " + total);

        // Step 4: use the remainder operator, to figure out what the remainder from the
        // result of the operation in step three, and 40.00, will be.
        double remainder = total % 40.00;
        System.out.println(remainder);

        // Step 5: create a boolean variable that assigns the value true, if the
        // remainder in step four is 0.00, or false if it's not zero.
        boolean isZero = (remainder == 0);

        // Step 6: output the boolean variable just to see what the result is.
        System.out.println("isZero = " + isZero);

        // Step 7: write an if-then statement that displays a message, 'got some
        // remainder', if the boolean in step five is not true.
        if (!isZero) {
            System.out.println("got some remainder");
        }
    }
}

// Output:
// total = 10000.0
// 0.0
// isZero = true