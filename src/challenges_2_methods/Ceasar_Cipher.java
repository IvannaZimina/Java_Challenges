/* --- TASK ---
Write a method,which takes in a String with a message and
returns a String with the message encrypted by the Ceasar cipher method.
Create two versions of this method.
In the first version,the Caesar shift is provided as an int as a second parameter and
in the second version some shift value is used automatically inside the method.
For the algorithm details visit this page:https://en.wikipedia.org/wiki/Caesar_cipher
*/

package challenge.task_2_methods;

public class Ceasar_Cipher {

    // I set a constant default shift value to 3
    private static final int DEFAULT_SHIFT = 3;

    // In the main method, I will test my encrypt methods with different messages and shifts
    public static void main(String[] args) {
        System.out.println(encrypt("Hello, World!", 3));
        System.out.println(encrypt("xyz XYZ", 2));
        System.out.println(encrypt("attack at dawn", 13));
        System.out.println(encrypt("Hello, World!"));
        System.out.println(encrypt("Khoor, Zruog!", -3));
    }

    // create the first version of encrypt - it uses default shift
    // It takes a message and returns encrypted message
    public static String encrypt(String message) {
        return encrypt(message, DEFAULT_SHIFT);
    }

    // create the second version of encrypt - it takes a shift as parameter
    // It takes message and shift, and returns encrypted message
    public static String encrypt(String message, int shift) {
        if (message == null) {
            return "";
        }

        // normalize the shift - I make it between 0 and 25
        int k = ((shift % 26) + 26) % 26;

        // create a StringBuilder to build the result string
        StringBuilder sb = new StringBuilder(message.length());

        // loop through each character in the message
        for (int i = 0; i < message.length(); i++) {
            // get the current character from the message
            char ch = message.charAt(i);

            // check if the character is an uppercase letter (A-Z)
            if (ch >= 'A' && ch <= 'Z') {
                int pos = ch - 'A'; // get the position of the letter in the alphabet (0-25)
                int newPos = (pos + k) % 26; // calculate the new position after shifting
                char enc = (char) ('A' + newPos); // convert the new position back to a character
                sb.append(enc); // add the encrypted character to the result

                // check if the character is a lowercase letter (a-z)
            } else if (ch >= 'a' && ch <= 'z') {
                int pos = ch - 'a'; // get the position of the letter in the alphabet (0-25)
                int newPos = (pos + k) % 26; // calculate the new position after shifting
                char enc = (char) ('a' + newPos); // convert the new position back to a character
                sb.append(enc); // add the encrypted character to the result

                // check if the character is not a letter (number, space, punctuation)
            } else {
                sb.append(ch); // add the character without changing it
            }
        }

        return sb.toString();
    }
}
