/* --- TASK ---
Write a method called isPalindrome with one int parameter called number. The method needs to return a boolean. It should return true if the number is a palindrome number otherwise it should return false. A palindrome number is a number which, when reversed, is equal to the original number. For example: 121, 12321, 1001 etc.
Examples:
isPalindrome(707) → should return true
isPalindrome(1221) → should return true
isPalindrome(11212) → should return false

*/
package challenge.task_4_loops;

public class PalindromicNumbers {

    // checking if a number is a palindrome - reads same forwards and backwards
    public static boolean isPalindrome(int number) {
        // saving original number for later comparison
        int original = number;

        // variable to build reversed number by extracting digits
        int reversed = 0;

        // looping while there are still digits to process
        while (number > 0) {
            // extracting last digit using modulo
            int digit = number % 10;

            // adding this digit to front of reversed number
            // multiplying by 10 to shift left, then adding new digit
            reversed = reversed * 10 + digit;

            // removing last digit by dividing by 10
            number = number / 10;
        }

        // comparing - if they match, it's a palindrome
        return original == reversed;
    }
}
