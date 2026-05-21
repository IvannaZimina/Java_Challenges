// Generic mapping example: Layer with Mappable, Point/Line, Park and River classes
package challenges_18_generic_classes;

import java.util.ArrayList;
import java.util.List;

// Generic mapping example: Layer<T extends Mappable>
public class GenericsClass {

	public static void main(String[] args) {
		// Create a layer for parks (points)
		Layer<Park> parkLayer = new Layer<>();
		parkLayer.add(new Park("Lahemaa National Park", 59.0, 26.0));
		parkLayer.add(new Park("Soomaa National Park", 58.4, 25.5));

		// Create a layer for rivers (lines)
		Layer<River> riverLayer = new Layer<>();
		double[][] riverCoords = { {58.5, 25.6}, {58.6, 25.7}, {58.7, 25.8} };
		riverLayer.add(new River("Pärnu River", riverCoords));

		// Render layers: this will call render on each element
		System.out.println("Park layer:");
		parkLayer.renderLayer();

		System.out.println("River layer:");
		riverLayer.renderLayer();
	}

}

// Mappable interface: anything that can be rendered on a map
interface Mappable {
	// Render the element as a String to stdout
	void render();

	// Helper: parse a single coordinate string "lat,lon" to double array
	static double[] parseLocation(String s) {
		String[] parts = s.split(",");
		return new double[] { Double.parseDouble(parts[0].trim()), Double.parseDouble(parts[1].trim()) };
	}
}

// Abstract Point: holds a single location (lat, lon)
abstract class Point implements Mappable {
	protected double[] location; // [lat, lon]

	Point(double lat, double lon) {
		this.location = new double[] { lat, lon };
	}

	// Helper to format location as string
	protected String locationString() {
		return String.format("[%.6f, %.6f]", location[0], location[1]);
	}
}

// Abstract Line: holds multiple locations
abstract class Line implements Mappable {
	protected double[][] locations; // array of [lat, lon] pairs

	Line(double[][] locations) {
		this.locations = locations;
	}

	// Helper to format locations as a printable string
	protected String locationsString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < locations.length; i++) {
			double[] p = locations[i];
			sb.append(String.format("[%.6f, %.6f]", p[0], p[1]));
			if (i < locations.length - 1) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}

// Park extends Point and implements render
class Park extends Point {
	private String name;

	Park(String name, double lat, double lon) {
		super(lat, lon);
		this.name = name;
	}

	// Render: print park name and its location
	@Override
	public void render() {
		System.out.println(String.format("Park: %s at %s", name, locationString()));
	}
}

// River extends Line and implements render
class River extends Line {
	private String name;

	River(String name, double[][] locations) {
		super(locations);
		this.name = name;
	}

	// Render: print river name and its coordinates
	@Override
	public void render() {
		System.out.println(String.format("River: %s along %s", name, locationsString()));
	}
}

// Generic Layer class that holds Mappable elements
class Layer<T extends Mappable> {
	private List<T> layerElements = new ArrayList<>();

	// Add a single element
	public void add(T element) {
		layerElements.add(element);
	}

	// Add multiple elements
	public void addAll(List<T> elements) {
		layerElements.addAll(elements);
	}

	// Render the whole layer: call render on each element
	public void renderLayer() {
		// Loop: execute render() on each element
		for (T e : layerElements) {
			e.render();
		}
	}
}
