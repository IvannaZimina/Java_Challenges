// Implements Mappable interface, Building and UtilityLine classes, and prints JSON-like properties
package challenges_17_interfaces;

import java.util.ArrayList;
import java.util.List;

// Main class for the interface exercise
public class InterfaceClass {

	public static void main(String[] args) {
		// Create a list of mappable items (buildings and utility lines)
		List<Mappable> items = new ArrayList<>();
		items.add(new Building("Sakala keskus", BuildingType.ENTERTAINMENT, Color.ORANGE, PointMarker.STAR));
		items.add(new Building("Town Hall", BuildingType.GOVERNMENT, Color.BLUE, PointMarker.CIRCLE));
		items.add(new UtilityLine("Karamelli tänav", UtilityType.FIBER_OPTIC, Color.GREEN, LineMarker.DOTTED));

		// Loop: print the JSON properties for each mappable item
		for (Mappable m : items) {
			// Use the default toJSON() which uses the interface static helper
			System.out.println(m.toJSON());
		}
	}

}

// Geometry types for shapes
enum Geometry {
	POINT, LINE, POLYGON
}

// Simple color enum used for markers
enum Color {
	ORANGE, BLUE, GREEN
}

// Point marker types (for point features)
enum PointMarker {
	STAR, CIRCLE, SQUARE
}

// Line marker types (for line features)
enum LineMarker {
	SOLID, DOTTED, DASHED
}

// Building type enum for usage
enum BuildingType {
	ENTERTAINMENT, GOVERNMENT, RESIDENTIAL
}

// Utility type enum
enum UtilityType {
	FIBER_OPTIC, WATER, ELECTRIC
}

// The Mappable interface: forces label, marker, and shape.
interface Mappable {
	// JSON template for properties. Text block keeps quotes intact.
	String JSON_PROPERTY = "\"properties\" :  { %s }";

	// Abstract methods to provide basic map information
	String getLabel();
	String getMarker();
	Geometry getShape();

	// Default method: build a JSON properties string for this instance
	default String toJSON() {
		// Use the static helper to build the inner properties, then wrap
		String props = Mappable.propertiesFor(this);
		return String.format(JSON_PROPERTY, props);
	}

	// Static helper: produce the comma-separated properties for any Mappable
	static String propertiesFor(Mappable m) {
		// Always include type, label, and marker
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\"type\" : \"%s\", ", m.getShape()));
		sb.append(String.format("\"label\" : \"%s\", ", m.getLabel()));
		sb.append(String.format("\"marker\" : \"%s\"", m.getMarker()));

		// Add extra fields depending on the runtime type
		if (m instanceof Building) {
			Building b = (Building) m;
			// Add building-specific fields: name and usage
			sb.append(String.format(", \"name\" : \"%s\", \"usage\" : \"%s\"", b.name, b.type));
		} else if (m instanceof UtilityLine) {
			UtilityLine u = (UtilityLine) m;
			// Add utility-specific fields: name and utility type
			sb.append(String.format(", \"name\" : \"%s\", \"utility\" : \"%s\"", u.name, u.utility));
		}

		return sb.toString();
	}
}

// Building is a point feature on the map
class Building implements Mappable {
	String name;
	BuildingType type;
	Color color;
	PointMarker marker;

	Building(String name, BuildingType type, Color color, PointMarker marker) {
		this.name = name;
		this.type = type;
		this.color = color;
		this.marker = marker;
	}

	// Return label used on the map (name and usage)
	@Override
	public String getLabel() {
		return name + " (" + type + ")";
	}

	// Return marker string built from color and marker type
	@Override
	public String getMarker() {
		return color + " " + marker;
	}

	// Buildings are points
	@Override
	public Geometry getShape() {
		return Geometry.POINT;
	}
}

// UtilityLine is a line feature on the map
class UtilityLine implements Mappable {
	String name;
	UtilityType utility;
	Color color;
	LineMarker marker;

	UtilityLine(String name, UtilityType utility, Color color, LineMarker marker) {
		this.name = name;
		this.utility = utility;
		this.color = color;
		this.marker = marker;
	}

	// Label for the line (name and utility type)
	@Override
	public String getLabel() {
		return name + " (" + utility + ")";
	}

	// Marker for lines uses color and line marker
	@Override
	public String getMarker() {
		return color + " " + marker;
	}

	// Utility lines are lines
	@Override
	public Geometry getShape() {
		return Geometry.LINE;
	}
}

// ======= output =======
// "properties" :  { "type" : "POINT", "label" : "Sakala keskus (ENTERTAINMENT)", "marker" : "ORANGE STAR", "name" : "Sakala keskus", "usage" : "ENTERTAINMENT" }
// "properties" :  { "type" : "POINT", "label" : "Town Hall (GOVERNMENT)", "marker" : "BLUE CIRCLE", "name" : "Town Hall", "usage" : "GOVERNMENT" }
// "properties" :  { "type" : "LINE", "label" : "Karamelli t?nav (FIBER_OPTIC)", "marker" : "GREEN DOTTED", "name" : "Karamelli t?nav", "utility" : "FIBER_OPTIC" }