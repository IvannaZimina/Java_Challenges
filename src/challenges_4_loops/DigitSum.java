/* --- TASK ---
Write a method that sums digits of a number. It should take in type int and should return an int. The method should only take a number that is a positive number. If a negative number is passed, it should return -1, meaning, an invalid value was passed. The method should parse out each digit from the number and sum the digits up.
So, if 125 is the value passed to the method, the code should sum each digit, in this case, 1 + 2 + 5, and return 8, as a value. If the number is a single digit number, simply return the number itself as the result.
*/
package challenges_4_loops;

public class DigitSum {
    public static void main(String[] args) {
        System.out.println(sumDigits(125)); // 8
        System.out.println(sumDigits(5)); // 5
        System.out.println(sumDigits(-10)); // -1
        System.out.println(sumDigits(0)); // 0
    }

    public static int sumDigits(int number) {
        if (number < 0) {
            return -1;
        }
        if (number < 10) {
            return number;
        }

        int sum = 0; // counter for sum of digits
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
