/* --- TASK ---
Write a method, which takes in a date and returns a zodiac sign as a String.
Use the enhanced switch statement as an expression.
Write 3 different versions of the method using method overloading for different ways to pass in a date.
*/

package challenges_3_switch;

// import statements for date handling and user input
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ZodiacSigns {

    public static String zodiac(LocalDate date) {
        if (date == null)
            return "Invalid date";
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // sent the data to the method with switch statement
        return zodiacByMonthDay(month, day);
    }

    // Validates the date, when user enter year, month and day as separate integers
    public static String zodiac(int year, int month, int day) {
        if (month < 1 || month > 12)
            return "Invalid date";
        if (day < 1)
            return "Invalid date";

        boolean leap = Year.isLeap(year);
        int maxDay = daysInMonth(month, leap);
        if (day > maxDay)
            return "Invalid date";

        return zodiacByMonthDay(month, day);
    }

    // Validates the date, when user enter a date string in one of the supported
    // formats
    public static String zodiac(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty())
            return "Invalid date";

        // formats that user can input
        DateTimeFormatter[] formats = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd.MM.yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("d-M-uuuu")
        };

        for (DateTimeFormatter f : formats) {
            try {
                LocalDate d = LocalDate.parse(dateStr.trim(), f);
                return zodiac(d);
            } catch (DateTimeParseException ignored) {
                // Try next format
            }
        }

        System.out.println("No supported date formats matched.");
        return "Invalid date";
    }

    // Western (tropical) zodiac boundaries:
    // Capricorn: Dec 22 – Jan 19
    // Aquarius : Jan 20 – Feb 18
    // Pisces : Feb 19 – Mar 20
    // Aries : Mar 21 – Apr 19
    // Taurus : Apr 20 – May 20
    // Gemini : May 21 – Jun 20
    // Cancer : Jun 21 – Jul 22
    // Leo : Jul 23 – Aug 22
    // Virgo : Aug 23 – Sep 22
    // Libra : Sep 23 – Oct 22
    // Scorpio : Oct 23 – Nov 21
    // Sagittarius: Nov 22 – Dec 21

    // Here an enhanced switch statement is using as an expression to determine the
    // zodiac sign based on the month and day.
    private static String zodiacByMonthDay(int month, int day) {
        if (month < 1 || month > 12 || day < 1)
            return "Invalid date";

        return switch (month) {
            case 1 -> (day < 20) ? "Capricorn" : "Aquarius"; // Jan
            case 2 -> (day < 19) ? "Aquarius" : "Pisces"; // Feb
            case 3 -> (day < 21) ? "Pisces" : "Aries"; // Mar
            case 4 -> (day < 20) ? "Aries" : "Taurus"; // Apr
            case 5 -> (day < 21) ? "Taurus" : "Gemini"; // May
            case 6 -> (day < 21) ? "Gemini" : "Cancer"; // Jun
            case 7 -> (day < 23) ? "Cancer" : "Leo"; // Jul
            case 8 -> (day < 23) ? "Leo" : "Virgo"; // Aug
            case 9 -> (day < 23) ? "Virgo" : "Libra"; // Sep
            case 10 -> (day < 23) ? "Libra" : "Scorpio"; // Oct
            case 11 -> (day < 22) ? "Scorpio" : "Sagittarius"; // Nov
            case 12 -> (day < 22) ? "Sagittarius" : "Capricorn"; // Dec
            default -> "Invalid date";
        };
    }

    // Helper method to get the number of days in a month (leap year only affects
    // February)
    private static int daysInMonth(int month, boolean leap) {
        int[] days_in_month = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (month == 2 && leap)
            return 29;
        return days_in_month[month - 1];
    }

    // Helper method to check if all strings in the array can be parsed as integers
    private static boolean isAllInts(String[] arr) {
        for (String s : arr) {
            if (!s.matches("[-+]?\\d+"))
                return false;
        }
        return true;
    }

    // main function to get data from input and print the results
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // helpful instructions for the user
        System.out.println("Enter a date to get the zodiac sign.");
        System.out.println("Supported formats: yyyy-MM-dd | dd.MM.yyyy | MM/dd/yyyy | d-M-uuuu");
        System.out.println("Or three numbers: YYYY M D (e.g., 2025 8 22).");
        System.out.println("Press Enter on an empty line to exit.");

        String line;
        do {
            System.out.print("\nDate: ");
            line = in.nextLine();

            // Check for empty input to exit the loop
            if (line == null || line.trim().isEmpty()) {
                System.out.println("Empty input. Completion.");
                break;
            }

            String trimmed = line.trim();
            String normalized = trimmed.replace(",", " ");

            // split the data into parts by spaces, tabs, or transfers
            String[] parts = normalized.split("\\s+");

            String result;
            if (parts.length == 3 && isAllInts(parts)) {
                try {
                    int year = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int day = Integer.parseInt(parts[2]);
                    result = zodiac(year, month, day);
                } catch (NumberFormatException e) {
                    result = "Invalid date";
                }
            } else {
                result = zodiac(trimmed);
            }

            System.out.println(trimmed + " -> " + result);

        } while (true);

        in.close();
    }

}

// ----------- Expected output -----------
// Date: 2000-12-23
// 2000-12-23 -> Capricorn

// Date: 2012 3 5
// 2012 3 5 -> Pisces

// Date: 1987.12.34
// 1987.12.34 -> Invalid date

// Date: 1987.12.23
// 1987.12.23 -> Invalid date

// Date: 23-03-2000
// 23-03-2000 -> Aries
