package challenges_1_operators;
public class LeapYear {
    // Can make it a method, if you feel comfortable doing so.

    static boolean isLeapYear(int year) {
        // from wiki:
        // - multiples of 400 -> leap years.
        // - multiples of 4, but not multiples of 100 -> leap years.
        // - everything else -> common years.
        boolean leap_year = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        return leap_year;
    }

    public static void main(String[] args) {

        // takes in an int variable with a year
        int year = 2024;

        boolean isLeap = isLeapYear(year);

        // tells a user if it’s a leap year or not.
        if (isLeap) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is NOT a leap year");
        }
    }
}
