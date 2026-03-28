/* --- TASK ---
Write a void method, which takes in a long variable,
representing the number of bytes in data,
which prints this number as a String using kilobytes, megabytes, gigabytes and terabytes.
*/

package challenges_2_methods;

import java.util.Scanner;

public class Bite_Coverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object
        long bytes;

        // class works until user enter the valid value (integer equal or more than 0)
        while (true) {
            System.out.print("Enter the number of bytes (integer equal or more than 0): ");
            String input = sc.nextLine().trim();

            try {
                bytes = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter an INTEGER number without letters or symbols.");
                continue;
            }

            if (bytes < 0) {
                System.out.println("Error: The number of bytes cannot be negative.");
                continue;
            }

            break;
        }

        printBytes(bytes);
        sc.close(); // Close the scanner to prevent resource leaks
    }

    public static void printBytes(long bytes) {
        // final like a constant, it cannot be changed after initialization
        final long KB = 1024L;
        final long MB = KB * 1024L;
        final long GB = MB * 1024L;
        final long TB = GB * 1024L;

        long kilobytes = bytes / KB;
        long megabytes = bytes / MB;
        long gigabytes = bytes / GB;
        long terabytes = bytes / TB;

        System.out.println("Bytes:     " + bytes);
        System.out.println("Kilobytes: " + kilobytes);
        System.out.println("Megabytes: " + megabytes);
        System.out.println("Gigabytes: " + gigabytes);
        System.out.println("Terabytes: " + terabytes);
    }
}
