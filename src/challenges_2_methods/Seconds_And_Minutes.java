/* --- TASK ---
In this challenge,we're going to create a method,that takes time,represented in seconds,as the parameter.
We'll then want to transform the seconds into hours.
Next,you'll display the time in hours with the remaining minutes and seconds in a String.
We'll do this transformation in two steps,which allows us to use overloaded methods.
We want to create two methods with the same name:getDurationString.
The first method has one parameter of type int,named seconds.
The second method has two parameters,named minutes and seconds,both int.
Both methods return a String in the format shown:
‘XXh YYm ZZs’where XX represents the number of hours,YY the number of minutes,and ZZ the number of seconds.
The first method should in turn call the second method to return its results.
Add input validation to the methods as a bonus.
*/

package challenge.task_2_methods;

public class Seconds_And_Minutes {
    public static void main(String[] args) {
        System.out.println(getDurationString(3945)); // 01h 05m 45s
        System.out.println(getDurationString(65)); // 00h 01m 05s
        System.out.println(getDurationString(0)); // 00h 00m 00s

        System.out.println(getDurationString(125, 5)); // 02h 05m 05s
        System.out.println(getDurationString(61, 59)); // 01h 01m 59s

        System.out.println(getDurationString(-10)); // Invalid value
        System.out.println(getDurationString(10, 75)); // Invalid value
        System.out.println(getDurationString(-1, 30)); // Invalid value
    }

    public static String getDurationString(int seconds) {
        if (seconds < 0) {
            return "Invalid value";
        }
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return getDurationString(minutes, remainingSeconds);
    }

    public static String getDurationString(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59) {
            return "Invalid value";
        }
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        String hh = pad2(hours);
        String mm = pad2(remainingMinutes);
        String ss = pad2(seconds);

        return hh + "h " + mm + "m " + ss + "s";
    }

    // Method to pad single-digit numbers with a leading zero
    private static String pad2(int value) {
        if (value < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }

}
