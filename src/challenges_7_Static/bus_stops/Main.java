package challenges_7_Static.bus_stops;

import java.io.File; // Used to work with normal file paths.
import java.io.IOException; // Used for file reading errors.
import java.nio.charset.StandardCharsets; // Used to read the file as UTF-8.
import java.util.ArrayList; // Used to store bus stops.
import java.util.List; // Used to keep a list of items.
import java.util.Locale; // Used to print numbers in a fixed format.
import java.util.Random; // Used to pick random stops.
import java.util.Scanner; // Used to read the file line by line.

public class Main {

    // This number says how many random pairs are printed.
    private static final int COMPARISON_COUNT = 5;
    // From the task: the program should read stops.txt.
    private static final String DEFAULT_STOPS_FILE = "stops.txt";

    public static void main(String[] args) {
        // From the task: print a start message before reading data.
        System.out.println("Reading bus stops data...");

        // First the program tries to find the file in the usual project folders.
        String stopsFile = findStopsFile();
        // This check stops the program when the file is missing.
        if (stopsFile == null) {
            System.out.println("Bus stops file was not found.");
            return;
        }

        // Now all valid bus stops are read from the file.
        List<BusStop> busStops = loadBusStops(stopsFile);
        // This check stops the program when there are too few stops.
        if (busStops.size() < 2) {
            System.out.println("Not enough bus stops were loaded.");
            return;
        }

        // This object helps choose random stops.
        Random random = new Random();

        // From the task: print the message before distance checks.
        System.out.println("Calculating distances between bus stops...");
        // From the task: print the message before area checks.
        System.out.println("Checking if bus stops are in the same area...");

        // From the task: repeat the comparison for several random pairs.
        // Every loop run prints one new pair of stops.
        for (int attempt = 0; attempt < COMPARISON_COUNT; attempt++) {
            // The first stop for this pair is picked here.
            BusStop first = pickRandomStop(busStops, random);
            // A second stop is picked so two different places can be compared.
            BusStop second = pickDifferentRandomStop(busStops, random, first);

            // From the task: calculate the distance between two bus stops.
            double distance = BusStop.getDistance(first, second);
            // From the task: check if the two stops are in the same area.
            boolean sameArea = BusStop.isSameArea(first, second);

            // One result line with the distance is printed here.
            System.out.printf(
                    Locale.ROOT,
                    "Distance between %s in %s and %s in %s is %.1f km%n",
                    first.name(),
                    first.area(),
                    second.name(),
                    second.area(),
                    distance);
            // The area comparison result is printed here.
            System.out.println("Are they in the same area? " + sameArea);
        }
    }

    // This helper finds the stops file in the usual project folders.
    private static String findStopsFile() {
        // Simple file names are checked first, then known project paths.
        List<String> candidates = List.of(
                DEFAULT_STOPS_FILE,
                "src/challenges_7_Static/bus_stops/stops.txt");

        // This loop checks each possible file path.
        for (String candidate : candidates) {
            // This check returns the first path that really exists.
            if (new File(candidate).exists()) {
                return candidate;
            }
        }
        return null;
    }

    // From the task: read the CSV file line by line and keep valid stops.
    private static List<BusStop> loadBusStops(String stopsFile) {
        // This list holds all good bus stops from the file.
        List<BusStop> busStops = new ArrayList<>();

        // Scanner reads the file line by line.
        try (Scanner scanner = new Scanner(new File(stopsFile), StandardCharsets.UTF_8)) {
            // This check skips the header row.
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // This loop reads one CSV line after another.
            while (scanner.hasNextLine()) {
                // One raw line is read from the file.
                String line = scanner.nextLine();
                // The raw line is turned into one BusStop object if the data is valid.
                BusStop busStop = parseBusStop(line);

                // This check skips empty or broken rows.
                if (busStop != null) {
                    // Only valid stops are saved.
                    busStops.add(busStop);
                }
            }
        } catch (IOException exception) {
            // This message shows a file reading problem.
            System.out.println("Could not read bus stops file: " + exception.getMessage());
        }

        return busStops;
    }

    // This helper turns one CSV row into one BusStop object.
    private static BusStop parseBusStop(String line) {
        // The raw CSV row is split into parts.
        List<String> columns = parseCsvLine(line);

        // This check makes sure the row has enough columns before parsing.
        if (columns.size() < 16) {
            return null;
        }

        // These column numbers come from the real stops.txt file.
        String id = getColumn(columns, 0);
        String name = getColumn(columns, 2);
        String latitudeText = getColumn(columns, 3);
        String longitudeText = getColumn(columns, 4);
        String area = getArea(columns);

        // This check makes sure the main data is not empty.
        if (id.isBlank() || name.isBlank() || latitudeText.isBlank() || longitudeText.isBlank()) {
            return null;
        }

        try {
            // Text values are converted to numbers for the record.
            double latitude = Double.parseDouble(latitudeText);
            double longitude = Double.parseDouble(longitudeText);
            return new BusStop(id, name, latitude, longitude, area);
        } catch (NumberFormatException exception) {
            // This check skips rows with bad latitude or longitude values.
            return null;
        }
    }

    // This helper splits one CSV line into columns.
    private static List<String> parseCsvLine(String line) {
        // The column list is built step by step.
        List<String> columns = new ArrayList<>();
        // This buffer keeps the current column text.
        StringBuilder current = new StringBuilder();
        // This flag tells if the parser is inside quotes.
        boolean insideQuotes = false;

        // This loop reads the line character by character.
        for (int index = 0; index < line.length(); index++) {
            char character = line.charAt(index);

            // This check keeps text inside quotes together.
            if (character == '"') {
                // Two quotes in a row mean one real quote inside the text.
                if (insideQuotes && index + 1 < line.length() && line.charAt(index + 1) == '"') {
                    current.append('"');
                    index++;
                } else {
                    // One quote changes the inside/outside state.
                    insideQuotes = !insideQuotes;
                }
                // This check splits columns at commas that are not inside quotes.
            } else if (character == ',' && !insideQuotes) {
                // The current column is finished and a new one starts.
                columns.add(current.toString().trim());
                current.setLength(0);
            } else {
                // Normal text goes into the current column.
                current.append(character);
            }
        }

        // The last column is added after the loop ends.
        columns.add(current.toString().trim());
        return columns;
    }

    // This helper returns one column safely.
    private static String getColumn(List<String> columns, int index) {
        // This check avoids index errors.
        if (index < 0 || index >= columns.size()) {
            return "";
        }
        return columns.get(index).trim();
    }

    // This helper reads the area field from the task data.
    private static String getArea(List<String> columns) {
        // The main area column is checked first.
        String area = getColumn(columns, 15);
        // This check uses a backup column if the main area field is empty.
        if (area.isBlank()) {
            // Some rows may keep the area in a different place.
            area = getColumn(columns, 14);
        }
        return area;
    }

    // This helper picks one random stop from the list.
    private static BusStop pickRandomStop(List<BusStop> busStops, Random random) {
        // A random number chooses one item from the list.
        return busStops.get(random.nextInt(busStops.size()));
    }

    // This helper picks a second stop that is different from the first one.
    private static BusStop pickDifferentRandomStop(List<BusStop> busStops, Random random, BusStop first) {
        // This check handles the rare case when there is only one stop.
        if (busStops.size() == 1) {
            return first;
        }

        // A second stop is picked and retried until it is not the same one.
        BusStop second = pickRandomStop(busStops, random);
        // This loop keeps trying until the second stop is different.
        while (second == first) {
            second = pickRandomStop(busStops, random);
        }
        return second;
    }
}
