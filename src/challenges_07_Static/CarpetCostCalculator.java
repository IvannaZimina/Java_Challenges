/* Carpet Cost Calculator
The Carpet Company has asked you to write an application that calculates the price of carpeting for rectangular rooms. To calculate the price, you multiply the area of the floor (width times length) by the price per square meter of carpet.
For example, the area of the floor that is 12 meters long and 10 meters wide is 120 square meters. To cover the floor with a carpet that costs 8€ per square meter would cost 960€.
Write a class with the name Carpet. The class needs two instance fields with names width and length of type double, and one static field with name squareMeterCost, which would contain the cost of one square meter of  the carpet.
The class needs to have one constructor with parameters width and length of type double, and it needs to initialize the fields. In case the width or length parameters are less than 0 it needs to set the value to 0.
Write the instance method named getArea without any parameters, which would return the calculated area (width * length).
Write the instance method named getTotalCost without any parameters, which would return the calculated total cost to cover the floor with a carpet.
Create several instances of the Carpet class in your main method with different sizes and print their costs.

*/

package challenges_07_Static;

public class CarpetCostCalculator {
	public static void main(String[] args) {
		// set price per square meter
		Carpet.squareMeterCost = 8.0;

		Carpet c1 = new Carpet(12, 10);
		Carpet c2 = new Carpet(5.5, 4.2);
		Carpet c3 = new Carpet(-3, 7); // negative width -> treated as 0

		printCarpetInfo(c1);
		printCarpetInfo(c2);
		printCarpetInfo(c3);
	}

	private static void printCarpetInfo(Carpet c) {
		System.out.printf("Carpet (%.2fm x %.2fm): area=%.2fm^2, total cost=%.2feuro%n",
				c.getWidth(), c.getLength(), c.getArea(), c.getTotalCost());
	}
}


class Carpet {
	private double width;
	private double length;

	// cost per square meter (shared for all carpets)
	public static double squareMeterCost;

	public Carpet(double width, double length) {
		this.width = width < 0 ? 0 : width;
		this.length = length < 0 ? 0 : length;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	public double getArea() {
		return width * length;
	}

	public double getTotalCost() {
		return getArea() * squareMeterCost;
	}
}
