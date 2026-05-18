package challenges_9_strings; // package declaration for the string challenges

import java.util.regex.Matcher; // regex matcher class
import java.util.regex.Pattern; // regex pattern class

public class SpaceInvetigator { // class that finds and fixes spacing issues

	// Main method to demonstrate the spaceInvestigator behavior
	public static void main(String[] args) {
		// Example string containing many spacing mistakes (at least 6 types)
		String text = "  This  is  a  test  .There are  multiple  errors  here , including a decimal 3 . 14 , and a dash - between words . Also a quote \" Hello ! \" and ( wrong ) spacing , and an extra   spaces.\n" +
					  "Another line - with - spaced hyphens and 2 .5 as a number .And an ending space " ; // input with deliberate spacing errors

		System.out.println("Original text:\n" + text + "\n---\n");

		String fixed = spaceInvestigator(text); // run the fixer pipeline and store result

		System.out.println("\n---\nFinal fixed text:\n" + fixed);
	}

	// Entry point: applies a sequence of helper methods and prints a report for each step
	public static String spaceInvestigator(String text) {
		System.out.println("Step 0: Starting space investigation");
		String current = text; // working copy of text

		current = trimOuterSpaces(current);             // remove leading/trailing spaces
		current = removeMultipleSpaces(current);        // collapse repeated spaces into one
		current = fixSpaceBeforePunctuation(current);   // remove space(s) before punctuation marks
		current = fixSpaceAfterPunctuation(current);    // ensure single space after punctuation
		current = fixDecimalSpacing(current);           // remove spaces around decimal points in numbers
		current = fixSpacingBeforeClosingSymbols(current); // remove space before ) ] } and closing quotes
		current = fixCommaBeforeClosingQuote(current);  // fix comma placement before closing quotation
		current = fixHyphenAndDashSpacing(current);     // normalize spacing around hyphens/dashes

		return current; // return the final corrected text
	}

	// Rule: remove leading and trailing spaces
	private static String trimOuterSpaces(String text) {
		String before = text;       // keep original for counting
		String after = text.trim(); // trim outer spaces
		int removed = before.length() - after.length(); // count removed characters
		System.out.println("Step 1 (trimOuterSpaces): removed outer spaces: " + removed);
		System.out.println(after);
		return after; // return trimmed text
	}

	// Rule: collapse multiple spaces into one
	private static String removeMultipleSpaces(String text) {
		Pattern p = Pattern.compile(" {2,}"); // pattern for two or more spaces
		Matcher m = p.matcher(text); // matcher for occurrences
		int count = 0;               // counter for replacements
		while (m.find()) count++;    // count occurrences
		String result = m.replaceAll(" "); // replace with single space
		System.out.println("Step 2 (removeMultipleSpaces): replaced " + count + " occurrences of multiple spaces");
		System.out.println(result);
		return result; // return corrected text
	}

	// Rule: remove spaces before punctuation like . , ; : ! ?
	private static String fixSpaceBeforePunctuation(String text) {
		Pattern p = Pattern.compile("\\s+([\\.,:;!?])"); // pattern: whitespace before punctuation
		Matcher m = p.matcher(text); // matcher for the pattern
		int count = 0;               // occurrence counter
		while (m.find()) count++;    // count matches
		String result = m.replaceAll("$1"); // remove the whitespace
		System.out.println("Step 3 (fixSpaceBeforePunctuation): removed spaces before punctuation: " + count);
		System.out.println(result);
		return result; // return corrected text
	}

	// Rule: ensure there is a single space after punctuation when starting a new word
	private static String fixSpaceAfterPunctuation(String text) {
		// Add a space after punctuation if it's directly followed by a non-space and not end of line
		Pattern p = Pattern.compile("([\\.,:;!?])(?=[^\\s\\\"'\\)\\]\\}])"); // punctuation followed by non-space
		Matcher m = p.matcher(text); // matcher for the pattern
		int count = 0;               // count of insertions
		StringBuffer sb = new StringBuffer(); // buffer for building result
		while (m.find()) {
			count++; // increment counter
			m.appendReplacement(sb, m.group(1) + " "); // insert a space after punctuation
		}
		m.appendTail(sb); // append remainder
		String result = sb.toString(); // build final string
		System.out.println("Step 4 (fixSpaceAfterPunctuation): ensured space after punctuation: " + count);
		System.out.println(result);
		return result; // return corrected text
	}

	// Rule: remove spaces around decimal point between digits (e.g., "3 . 14" -> "3.14")
	private static String fixDecimalSpacing(String text) {
		Pattern p = Pattern.compile("(\\d)\\s*\\.\\s*(\\d)"); // pattern for digit . digit with optional spaces
		Matcher m = p.matcher(text);          // matcher for decimals
		int count = 0;                        // counter for replacements
		StringBuffer sb = new StringBuffer(); // buffer for building result
		while (m.find()) {
			count++;                          // increment counter
			m.appendReplacement(sb, m.group(1) + "." + m.group(2)); // replace with no spaces around dot
		}
		m.appendTail(sb);               // append remainder
		String result = sb.toString();  // build final string
		System.out.println("Step 5 (fixDecimalSpacing): fixed decimal spacing occurrences: " + count);
		System.out.println(result);
		return result; // return corrected text
	}

	// Rule: remove spaces before closing symbols like ) ] } or closing quotes
	private static String fixSpacingBeforeClosingSymbols(String text) {
		Pattern p = Pattern.compile("\\s+([)\\]\\}\"'])"); // whitespace before closing symbols
		Matcher m = p.matcher(text); // matcher for occurrences
		int count = 0; // counter
		while (m.find()) count++;    // count matches
		String result = m.replaceAll("$1"); // remove space before closing symbol
		System.out.println("Step 6 (fixSpacingBeforeClosingSymbols): removed spaces before closing symbols: " + count);
		System.out.println(result);
		return result; // return corrected text
	}

	// Rule: remove space before a comma that is followed by a closing quotation mark
	private static String fixCommaBeforeClosingQuote(String text) {
		// remove spaces before a comma followed by a quote: " ," -> ","
		Pattern p1 = Pattern.compile("\\s+,\\s*([\"'])"); // pattern for space + comma + optional space + quote
		Matcher m1 = p1.matcher(text);          // matcher for the pattern
		int count1 = 0;                         // occurrence counter
		StringBuffer sb1 = new StringBuffer();  // buffer for result
		while (m1.find()) {
			count1++;                           // increment counter
			m1.appendReplacement(sb1, "," + m1.group(1)); // replace with comma directly before the quote
		}
		m1.appendTail(sb1); // append remainder
		String interim = sb1.toString(); // build final string

		System.out.println("Step 7 (fixCommaBeforeClosingQuote): fixed occurrences: " + count1);
		System.out.println(interim);
		return interim; // return corrected text
	}

	// Rule: normalize spacing around hyphens/dashes used between words or numbers
	private static String fixHyphenAndDashSpacing(String text) {
		// remove space around hyphen when between letters or digits: a - b -> a-b
		Pattern p = Pattern.compile("([A-Za-z0-9])\\s+-\\s+([A-Za-z0-9])"); // pattern for spaced hyphens
		Matcher m = p.matcher(text);          // matcher for the pattern
		int count = 0;                        // counter
		StringBuffer sb = new StringBuffer(); // buffer for building result
		while (m.find()) {
			count++;                          // increment counter
			m.appendReplacement(sb, m.group(1) + "-" + m.group(2)); // replace with tight hyphen
		}
		m.appendTail(sb);               // append remainder
		String result = sb.toString();  // build final string
		System.out.println("Step 8 (fixHyphenAndDashSpacing): normalized hyphen/dash spacing occurrences: " + count);
		System.out.println(result);
		return result; // return corrected text
	}
}

