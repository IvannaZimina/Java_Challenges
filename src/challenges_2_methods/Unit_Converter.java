/* --- TASK ---
Create two methods with the same name: convertToCentimeters.
The first method has one parameter of type int, which represents the entire height in inches.
You'll convert inches to centimeters, in this method, and pass back the number of centimeters, as a double.
The second method has two parameters of type int, one to represent height in feet,
and one to represent the remaining height in inches.
So, if a person is 5 foot, 8 inches, the values 5 for feet and 8 for inches would be passed to this method.
This method will convert feet and inches to just inches,
then call the first method, to get the number of centimeters, also returning the value as a double.
Both methods should return a real number or decimal value for total height in centimeters.
Call both methods and print out the results.
*/

package challenges_2_methods;

public class Unit_Converter {

    public static final double INCH_TO_CM = 2.54;

    public static void main(String[] args) {
        System.out.println("Height in centimeters (10 inches): " + convertToCentimeters(10)); // 25.4
        System.out.println("Height in centimeters (5 feet 8 inches): " + convertToCentimeters(5, 8)); // 172.72

        System.out.println("Height in centimeters (11.5 inches): " + convertToCentimeters(11.5)); // 29.21
        System.out.println("Height in centimeters (1 ft 0 in): " + convertToCentimeters(1, 0)); // 30.48

        System.out.println("Invalid value: Error: " + convertToCentimeters(-3)); // -1.0
        System.out.println("Invalid values: Error: " + convertToCentimeters(3, -2)); // -1.0
        System.out.println("Invalid values: Error: " + convertToCentimeters(3, 12.0)); // -1.0
    }

    public static double convertToCentimeters(double inches) {
        if (inches < 0) {
            return -1.0;
        }
        return inches * INCH_TO_CM;
    }

    public static double convertToCentimeters(int feet, double inches) {
        if (feet < 0 || inches < 0 || inches >= 12) {
            return -1.0;
        }
        double totalInches = feet * 12 + inches;
        return convertToCentimeters(totalInches);
    }
}