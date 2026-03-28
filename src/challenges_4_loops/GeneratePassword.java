/* --- TASK ---
Write a method, which would generate a password of a given length,
which would include numbers, small and capital letters, and special characters.
A method should take in one int argument of minimum size 8
and return a String with a password.
All positions and numbers of symbols in the password must be random,
but there must be always at least one number, one small and one capital letter,
and one special character.
Use Math.random() to generate random numbers.
*/
package challenges_4_loops;

public class GeneratePassword {
    // need to create a password with random letters, numbers, and symbols
    public static String generatePassword(int length) {
        // checking if length is at least 8 - safety requirement
        if (length < 8) {
            return "";
        }

        // creating strings with all character types - like separate pools
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String special = "!@#$%^&*()-_+=";
        // combining all characters into one big string for random picking
        String allChars = lowercase + uppercase + numbers + special;

        String password = "";

        // must have at least one of each type, so starting with those:
        // picking random lowercase letter
        password += lowercase.charAt((int) (Math.random() * lowercase.length()));
        // picking random uppercase letter
        password += uppercase.charAt((int) (Math.random() * uppercase.length()));
        // pick one random number
        password += numbers.charAt((int) (Math.random() * numbers.length()));
        // pick one random special character
        password += special.charAt((int) (Math.random() * special.length()));

        for (int i = password.length(); i < length; i++) {
            // filling up the password with random characters until reaching desired length
            password += allChars.charAt((int) (Math.random() * allChars.length()));
        }

        // shuffling the password so required characters mix throughout
        // converting string to array so can swap characters around
        char[] passwordArray = password.toCharArray();

        // going through each position and randomly swapping with another
        for (int i = 0; i < passwordArray.length; i++) {
            // picking random position to swap with
            int randomPos = (int) (Math.random() * passwordArray.length);

            // using temp variable to swap these two characters
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomPos];
            passwordArray[randomPos] = temp;
        }

        // converting shuffled array back to string and returning
        return new String(passwordArray);
    }
}
