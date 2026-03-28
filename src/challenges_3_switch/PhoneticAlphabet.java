/* --- TASK ---
In this challenge, we'll be using the NATO phonetic alphabet to replace a character or letter, with NATO's standardized word for that letter.
In radio transmissions, the word car, "C", "A", "R", would be read, "Charlie Alfa Romeo", for clarity.
We'll take a single character and return the matching word from the NATO phonetic alphabet, shown at the link below.
https://en.wikipedia.org/wiki/NATO_phonetic_alphabet
Create a new char variable.
Use the traditional switch statement (with a colon in case labels) that tests the value in the variable from Step 1.
Create cases for the characters.
Display a message in each case block, with the letter and the NATO word, then break.
Add a default block, which displays the letter with a message saying not found.
*/

package challenges_3_switch;

import java.util.Scanner;

public class PhoneticAlphabet {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner user_input = new Scanner(System.in);

        // alphabet letters A-Z
        char[] letters = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // corresponding NATO words
        String[] nato = {
                "Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett",
                "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango",
                "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu"
        };

        // string to hold user input
        String line;

        // ask user until he enters an empty line
        do {
            // ask user for letter
            System.out.print("Enter one Latin letter (Blank line to complete): ");
            // saving user input value in variable
            line = user_input.nextLine();

            // if user input is empty, we break the loop and end the program
            if (line == null || line.trim().isEmpty()) {
                System.out.println("Empty input. Completion.");
                break;
            }

            // make the first character uppercase to match the letters array
            char letter = line.trim().charAt(0);
            char upper = Character.toUpperCase(letter);

            // matching the letter with the NATO word
            boolean found = false;
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == upper) {
                    System.out.println("Letter: " + letter + " → NATO: " + nato[i]);
                    found = true;
                    break;
                }
            }

            // exception handling if the letter is not found in the NATO alphabet
            if (!found) {
                System.out.println("Letter: " + letter + " → not found in NATO alphabet");
            }
        } while (true);

        // required to close the Scanner object to prevent resource leaks
        user_input.close();
    }
}

// --------- Expected Output: -------------------

// Enter one Latin letter (Blank line to complete): K
// Letter: K ? NATO: Kilo

// Enter one Latin letter (Blank line to complete): s
// Letter: s ? NATO: Sierra

// Enter one Latin letter (Blank line to complete): b
// Letter: b ? NATO: Bravo

// Enter one Latin letter (Blank line to complete):
// Empty input. Completion.