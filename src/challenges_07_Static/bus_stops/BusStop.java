package challenges_07_Static.bus_stops;

import java.util.Objects;

// From the task: this record stores one bus stop.
public record BusStop(String id, String name, double latitude, double longitude, String area) {

    // From the task: calculate distance with the Haversine formula.
    public static double getDistance(BusStop first, BusStop second) {
        // This is the Earth radius in kilometers.
        double earthRadiusKm = 6371.0;

        // Latitude and longitude are turned into radians.
        // The formula needs radians, not degrees.
        double latitudeDelta = Math.toRadians(second.latitude() - first.latitude());
        double longitudeDelta = Math.toRadians(second.longitude() - first.longitude());

        // The original latitudes are also needed in radians.
        double startLatitude = Math.toRadians(first.latitude());
        double endLatitude = Math.toRadians(second.latitude());

        // This formula finds the distance between two points on the Earth.
        // It gives the curved distance over the globe.
        double a = Math.sin(latitudeDelta / 2) * Math.sin(latitudeDelta / 2)
                + Math.cos(startLatitude) * Math.cos(endLatitude)
                        * Math.sin(longitudeDelta / 2) * Math.sin(longitudeDelta / 2);
        // This is the second part of the Haversine formula.
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // The final result is the distance in kilometers.
        return earthRadiusKm * c;
    }

    // From the task: check if two stops are in the same area.
    public static boolean isSameArea(BusStop first, BusStop second) {
        // Cleaned text is compared so spaces do not break the result.
        return Objects.equals(normalizeText(first.area()), normalizeText(second.area()));
    }

    // This helper removes extra spaces before comparison.
    private static String normalizeText(String text) {
        // This check handles missing text safely.
        if (text == null) {
            return "";
        }
        // Spaces are removed from both ends.
        return text.trim();
    }
}
