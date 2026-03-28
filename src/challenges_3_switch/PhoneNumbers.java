/* --- TASK ---
Write a method, which takes in a phone number in a full format with a country code and returns a corresponding country name as a String.
Add up to 10 countries which are relevant for our region.
Use the enhanced switch statement as an expression.
Write two versions of the method using method overloading for phone numbers in a String and a Long formats.
*/

package challenges_3_switch;

public class PhoneNumbers {

    // Method accept phone number as String and returns a country name derived from
    // the country code.
    public static String countryByPhone(String rawPhone) {
        // Validate input
        if (rawPhone == null) {
            return "Invalid phone";
        }
        String normalized = rawPhone
                .trim()
                .replace(" ", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "");

        // Remove leading '+' if present.
        if (normalized.startsWith("+")) {
            normalized = normalized.substring(1);
        }

        // must start with a digit and have enough length.
        if (normalized.isEmpty() || !Character.isDigit(normalized.charAt(0))) {
            return "Invalid phone";
        }

        // Extract the country code from the normalized number.
        String code = extractCountryCode(normalized);

        // From task: Add up to 10 countries which are relevant for our region.
        // From task: Use the enhanced switch statement as an expression.
        String country = switch (code) {
            case "372" -> "Estonia";
            case "371" -> "Latvia";
            case "370" -> "Lithuania";
            case "358" -> "Finland";
            case "46" -> "Sweden";
            case "47" -> "Norway";
            case "45" -> "Denmark";
            case "48" -> "Poland";
            case "380" -> "Ukraine";
            case "49" -> "Germany";
            default -> "Unknown country";
        };

        return country;
    }

    // accept phone number as long, convert to String and reuse the main logic.
    public static String countryByPhone(long phone) {
        String normalized = String.valueOf(phone);
        return countryByPhone(normalized);
    }

    // Helper: extract country code with priority for longer matches.
    // Check known codes in descending length order (3 → 2 → 1).
    private static String extractCountryCode(String digitsOnly) {
        String[] knownCodes = {
                "380", // Ukraine
                "372", // Estonia
                "371", // Latvia
                "370", // Lithuania
                "358", // Finland
                "49", // Germany
                "48", // Poland
                "47", // Norway
                "46", // Sweden
                "45" // Denmark
        };

        for (String code : knownCodes) {
            if (digitsOnly.startsWith(code)) {
                return code;
            }
        }

        // If the country code is not recognized, return an empty string or a default
        // value.
        return "";
    }

    public static void main(String[] args) {
        // Demo data for String overload
        String[] samplesStr = {
                "+37251234567", // Estonia
                "+371 21234567", // Latvia (spaces)
                "+370-612-34567", // Lithuania (hyphens)
                "+358401234567", // Finland
                "+46(70)1234567", // Sweden (parentheses)
                "+47 41234567", // Norway
                "+45 12 34 56 78", // Denmark
                "+48 512 345 678", // Poland
                "+380 50 123 45 67", // Ukraine
                "+49 151 23456789", // Germany
                "+9991234567" // Unknown
        };

        System.out.println("=== String overload ===");
        for (String s : samplesStr) {
            System.out.println(s + " -> " + countryByPhone(s));
        }

        // Demo data for long overload (same numbers without '+' and formatting)
        long[] samplesLong = {
                37251234567L, 37121234567L, 37061234567L, 358401234567L,
                46701234567L, 4741234567L, 4512345678L, 48512345678L,
                380501234567L, 4915123456789L, 9991234567L
        };

        System.out.println("\n=== long overload ===");
        for (long v : samplesLong) {
            System.out.println(v + " -> " + countryByPhone(v));
        }
    }
}

// Expected output:

// === String overload ===
// +37251234567 -> Estonia
// +371 21234567 -> Latvia
// +370-612-34567 -> Lithuania
// +358401234567 -> Finland
// +46(70)1234567 -> Sweden
// +47 41234567 -> Norway
// +45 12 34 56 78 -> Denmark
// +48 512 345 678 -> Poland
// +380 50 123 45 67 -> Ukraine
// +49 151 23456789 -> Germany
// +9991234567 -> Unknown country

// === long overload ===
// 37251234567 -> Estonia
// 37121234567 -> Latvia
// 37061234567 -> Lithuania
// 358401234567 -> Finland
// 46701234567 -> Sweden
// 4741234567 -> Norway
// 4512345678 -> Denmark
// 48512345678 -> Poland
// 380501234567 -> Ukraine
// 4915123456789 -> Germany
// 9991234567 -> Unknown country