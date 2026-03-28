/* --- TASK ---
Write a boolean method which takes in an integer number and checks if it is a Fibonacci number. 
You can read what a Fibonacci number is here:
https://en.wikipedia.org/wiki/Fibonacci_sequence
from wiki:
and 𝐹𝑛 = 𝐹𝑛 − 1 + 𝐹𝑛 − 2 for n > 1.
*/
package challenges_4_loops;

public class FibonacciNumbers {
    public static void main(String[] args) {
        // Demo calls from this task: Fibonacci and non-Fibonacci examples.
        System.out.println(isFibonacci(0)); // true
        System.out.println(isFibonacci(1)); // true
        System.out.println(isFibonacci(8)); // true
        System.out.println(isFibonacci(10)); // false
        System.out.println(isFibonacci(-5)); // false
    }

    public static boolean isFibonacci(int number) {
        // negative input is treated as not a Fibonacci number.
        if (number < 0) {
            return false;
        }

        // From wiki: the starting Fibonacci values are 0 and 1.
        if (number == 0 || number == 1) {
            return true;
        }

        // two neighboring Fibonacci values
        int previous = 0;
        int current = 1;

        // from wiki: each next value is the sum of the previous two (Fn = Fn-1 + Fn-2).
        // The loop builds the sequence until current reaches or passes input number.
        while (current < number) {
            // This line calculates the next Fibonacci value.
            int next = previous + current;
            // This line shifts the pair forward by one step.
            previous = current;
            current = next;
        }

        // if generated value equals input, this number belongs to Fibonacci sequence.
        return current == number;
    }

}
