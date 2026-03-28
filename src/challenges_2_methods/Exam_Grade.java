/* --- TASK ---
Write a method,which takes in an int variable with an exam score and
returns a grade from“A”to“F”according to a European university grade system.
*/

package challenges_2_methods;

// W3 docs: The Scanner class is used to get user input, and it is found in the java.util package.
import java.util.Scanner;

public class Exam_Grade {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        int score = -1; // Initialize score with an invalid value
        boolean valid = false;

        // user enter a score until it is valid (between 0 and 100, and an integer)
        do {
            System.out.print("Enter grade (0..100): ");
            String user_input = myObj.nextLine(); // Read user input as a String

            try {
                score = Integer.parseInt(user_input);

                if (score < 0 || score > 100) {
                    System.out.println("Error: The score must be between 0 and 100.");
                } else {
                    valid = true; // Valid input
                    System.out.println("Entered score: " + score);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter an integer without letters or symbols.");
            }
        } while (!valid);

        String grade = gradeByIfElse(score);
        System.out.println("Grade: " + grade);
        myObj.close();
    }

    private static String gradeByIfElse(int score) {
        if (score >= 90)
            return "A";
        else if (score >= 80)
            return "B";
        else if (score >= 70)
            return "C";
        else if (score >= 60)
            return "D";
        else if (score >= 50)
            return "E";
        else
            return "F";
    }
}
