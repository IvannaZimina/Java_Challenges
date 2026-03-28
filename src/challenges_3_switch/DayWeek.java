/*  --- TASK ---
Create a method called printDayOfWeek, that takes an int parameter called day but doesn't return any values.
Use the enhanced switch statement, to return the name of the day,
based on the parameter passed to the switch statement,
so that 1 will return “Monday", 2 will return “Tuesday" and so on.
Any number not between 1 and 7, should return “Invalid day".
Note that return here means the value returned from the enhanced switch statement.
Use the enhanced switch statement as an expression, returning the result to a String named dayOfTheWeek.
Print both the day variable and the dayOfTheWeek variable.
In the main method, call this method for the values 0 through 7.
*/

package challenge.task_3_switch;

public class DayWeek {

    // Create a method named printDayOfWeek
    public static void printDayOfWeek(int day) {

        // Use the enhanced switch statement as an expression.
        String dayOfTheWeek = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid day";
        };

        // Print both the 'day' variable and the 'dayOfTheWeek' variable.
        System.out.println("day = " + day + " | dayOfTheWeek = " + dayOfTheWeek);
    }

    public static void main(String[] args) {
        // In the main method, call the method for the values 0 through 7 (inclusive).
        for (int d = 0; d <= 7; d++) {
            printDayOfWeek(d);
        }
    }
}