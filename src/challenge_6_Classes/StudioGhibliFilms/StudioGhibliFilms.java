/*
Create a class Film, which would have private fields title, director, producer, release date and running time (and description as an extra challenge). Create a constructor, as well as getters for the fields.
Write a program that reads data from a text file with data about Studio Ghibli films using a Scanner, which would create a class Film object for each film with corresponding data from the file (at least 5 films). Create a text-based user interface to show a user information about films.
The data source: https://ghibliapi.vercel.app/films
You need to save this data into a text file, which the program will read. 
Reading data from the file:

```java
File file = new File("./src/challenge_6_Classes/StudioGhibliFilms/data.json");
Scanner scanner = new Scanner(file);
while (scanner.hasNextLine()) {
    System.out.println(scanner.nextLine());
}
scanner.close();
```
*/

package challenge_6_Classes.StudioGhibliFilms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudioGhibliFilms {
    // Path to the JSON file.
    private static final String DATA_PATH = "./src/challenge_6_Classes/StudioGhibliFilms/data.json";

    // Start program: load films and open menu.
    public static void main(String[] args) {
        // Load films from file.
        List<Film> films = loadFilmsFromFile(DATA_PATH);
        // Check if films were loaded.
        if (films.isEmpty()) {
            System.out.println("No films were loaded. Check the data file: " + DATA_PATH);
            return;
        }
        // Run the text-based menu.
        runTextMenu(films);
    }

    // Read JSON file and build Film objects.
    private static List<Film> loadFilmsFromFile(String path) {
        // List for all films.
        List<Film> films = new ArrayList<>();

        try {
            // Open file and read full text.
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");

            // JSON text from the file.
            String json = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            // Find each JSON object and map fields.
            Pattern objectPattern = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);
            // Matcher for JSON objects.
            Matcher objectMatcher = objectPattern.matcher(json);

            // Loop through all found objects.
            while (objectMatcher.find()) {
                // One film object as text.
                String objectContent = objectMatcher.group(1);

                // Read needed fields.
                String title = extractStringField(objectContent, "title");
                String director = extractStringField(objectContent, "director");
                String producer = extractStringField(objectContent, "producer");
                String releaseDate = extractStringField(objectContent, "release_date");
                String runningTime = extractStringField(objectContent, "running_time");
                String description = extractStringField(objectContent, "description");

                // Add film only if required fields exist.
                if (title != null && director != null && producer != null
                        && releaseDate != null && runningTime != null) {
                    // Create and add film to the list.
                    films.add(new Film(title, director, producer, releaseDate, runningTime, description));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + path);
        }

        return films;
    }

    // Get one string field value from object text.
    private static String extractStringField(String objectContent, String fieldName) {
        Pattern fieldPattern = Pattern.compile("\"" + fieldName + "\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);
        Matcher matcher = fieldPattern.matcher(objectContent);

        // If field not found, return null.
        if (!matcher.find()) {
            return null;
        }

        // Clean escaped chars from JSON.
        return matcher.group(1)
                .replace("\\n", "\n")
                .replace("\\\"", "\"");
    }

    // Show main menu and process user choice.
    private static void runTextMenu(List<Film> films) {
        // Scanner for console input.
        Scanner input = new Scanner(System.in);

        // Keep menu open until exit.
        while (true) {
            System.out.println("\n=== Studio Ghibli Films ===");
            System.out.println("1. Show all film titles");
            System.out.println("2. Show details by film number");
            System.out.println("3. Search films by director");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            // Check if input is still available.
            if (!input.hasNextLine()) {
                System.out.println("Input is closed. Exiting.");
                return;
            }
            // User's menu choice.
            String choice = input.nextLine().trim();

            // Handle selected option.
            switch (choice) {
                case "1":
                    printFilmTitles(films);
                    break;
                case "2":
                    printFilmDetailsByNumber(films, input);
                    break;
                case "3":
                    searchByDirector(films, input);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
        }
    }

    // Print all film titles with numbers.
    private static void printFilmTitles(List<Film> films) {
        System.out.println("\nFilm list:");
        // Print each title.
        for (int i = 0; i < films.size(); i++) {
            System.out.println((i + 1) + ". " + films.get(i).getTitle());
        }
    }

    // Show full info for selected film.
    private static void printFilmDetailsByNumber(List<Film> films, Scanner input) {
        // Show titles to help user select.
        printFilmTitles(films);
        // Ask for film number.
        System.out.print("Enter film number: ");

        if (!input.hasNextLine()) {
            System.out.println("Input is closed.");
            return;
        }
        // User input for film number.
        String numberText = input.nextLine().trim();

        try {
            // Convert user number to list index.
            int index = Integer.parseInt(numberText) - 1;
            // Check if index is valid.
            if (index < 0 || index >= films.size()) {
                System.out.println("Film number is out of range.");
                return;
            }

            // Film to print.
            Film film = films.get(index);
            System.out.println("\nTitle: " + film.getTitle());
            System.out.println("Director: " + film.getDirector());
            System.out.println("Producer: " + film.getProducer());
            System.out.println("Release date: " + film.getReleaseDate());
            System.out.println("Running time: " + film.getRunningTime() + " minutes");
            // Print description if it exists.
            if (film.getDescription() != null && !film.getDescription().isBlank()) {
                System.out.println("Description: " + film.getDescription());
            }
        } catch (NumberFormatException e) {
            // User entered non-number text.
            System.out.println("Please enter a valid number.");
        }
    }

    // Search films by director name.
    private static void searchByDirector(List<Film> films, Scanner input) {
        // Ask for director name.
        System.out.print("Enter director name: ");

        if (!input.hasNextLine()) {
            System.out.println("Input is closed.");
            return;
        }

        // Lowercase query for easier compare.
        String directorQuery = input.nextLine().trim().toLowerCase();
        // True if at least one match exists.
        boolean found = false;

        // Check all films and print matches.
        for (Film film : films) {
            // If director name contains query, print it.
            if (film.getDirector().toLowerCase().contains(directorQuery)) {
                if (!found) {
                    System.out.println("\nMatches:");
                }
                // Mark that we found at least one match.
                found = true;
                System.out.println("- " + film.getTitle() + " (" + film.getReleaseDate() + ")");
            }
        }

        if (!found) {
            System.out.println("No films found for that director.");
        }
    }

    // Film class with private fields, constructor, and getters.
    public static class Film {
        // Film data.
        private final String title;
        private final String director;
        private final String producer;
        private final String releaseDate;
        private final String runningTime;
        private final String description;

        // Create film object.
        public Film(String title, String director, String producer,
                String releaseDate, String runningTime, String description) {
            this.title = title;
            this.director = director;
            this.producer = producer;
            this.releaseDate = releaseDate;
            this.runningTime = runningTime;
            this.description = description;
        }

        // --- getters ---
        public String getTitle() {
            return title;
        }

        public String getDirector() {
            return director;
        }

        public String getProducer() {
            return producer;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getRunningTime() {
            return runningTime;
        }

        public String getDescription() {
            return description;
        }
    }
}
