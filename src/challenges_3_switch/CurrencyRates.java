/* --- TASK ---
Write two methods which would convert euros to any European currency which is not a euro and vice versa.
Methods should take in an amount of money as a Double and a currency label as a String.
*/

package challenge.task_3_switch;

public class CurrencyRates {

    // Returns exchange rate for 1 EUR in selected target currency.
    // Example: PLN 4.30 means 1 EUR = 4.30 PLN.
    private static double rateFromEuro(String currency) {
        // check for null or empty input
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency code is empty");
        }

        // Normalize currency code to uppercase and trim whitespace
        String code = currency.trim().toUpperCase();

        // Return exchange rate based on currency code with switch expression
        return switch (code) {
            case "PLN" -> 4.30;
            case "SEK" -> 11.20;
            case "NOK" -> 11.50;
            case "DKK" -> 7.46;
            case "GBP" -> 0.85;
            case "CHF" -> 0.96;
            default -> throw new IllegalArgumentException("Unsupported currency: " + code);
        };
    }

    // Converts EUR amount to selected foreign currency.
    public static Double euroToCurrency(Double amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount is null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be >= 0");
        }

        // Get exchange rate for 1 EUR in target currency and calculate converted
        // amount.
        double rate = rateFromEuro(currency);
        return amount * rate;
    }

    // Converts amount from selected foreign currency back to EUR.
    public static Double currencyToEuro(Double amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount is null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be >= 0");
        }

        // Get exchange rate for 1 EUR in target currency and calculate converted
        // amount.
        double rate = rateFromEuro(currency);
        return amount / rate;
    }

    // One print method for both directions.
    // toEuro = false -> EUR to selected currency
    // toEuro = true -> selected currency to EUR
    private static void printConversion(Double amount, String currency, boolean toEuro) {
        // If toEuro is true, use reverse conversion, otherwise use forward conversion.
        Double result = toEuro
                ? currencyToEuro(amount, currency)
                : euroToCurrency(amount, currency);

        // Choose what to print on the left and right side.
        String fromCode = toEuro ? currency.toUpperCase() : "EUR";
        String toCode = toEuro ? "EUR" : currency.toUpperCase();

        // One common output format for all cases.
        System.out.printf("%.2f %s -> %.2f %s%n", amount, fromCode, result, toCode);
    }

    public static void main(String[] args) {
        // List of currencies to test.
        String[] currencies = { "PLN", "SEK", "NOK", "DKK", "GBP" };

        System.out.println("EUR -> currency:");
        // Go through the list and convert 100 EUR to each currency.
        for (String code : currencies) {
            printConversion(100.0, code, false);
        }

        System.out.println("\nCurrency -> EUR:");
        // Go through the same list and convert 100 units back to EUR.
        for (String code : currencies) {
            printConversion(100.0, code, true);
        }
    }
}

// --- EXPECTED OUTPUT ---

// EUR -> currency:
// 100,00 EUR -> 430,00 PLN
// 100,00 EUR -> 1120,00 SEK
// 100,00 EUR -> 1150,00 NOK
// 100,00 EUR -> 746,00 DKK
// 100,00 EUR -> 85,00 GBP

// Currency -> EUR:
// 100,00 PLN -> 23,26 EUR
// 100,00 SEK -> 8,93 EUR
// 100,00 NOK -> 8,70 EUR
// 100,00 DKK -> 13,40 EUR
// 100,00 GBP -> 117,65 EUR
