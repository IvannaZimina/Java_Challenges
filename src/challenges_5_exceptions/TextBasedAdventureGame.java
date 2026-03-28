/* --- TASK ---
Write first two levels of menu screens of a text-based adventure game like the Oregon Trail. The first menu level must have at least 5 different choices and the second menu level may just have some arbitrary texts. A user must enter a number to proceed to the second menu level or exit the game, and Q (or any other key of your choice) to return to the first menu level. The game must ignore any entered symbols, except numbers and Q key. The game must have a title, which is shown above the menus.
An example of a classic text-based adventure game:
https://www.visitoregon.com/the-oregon-trail-game-online/
https://en.wikipedia.org/wiki/The_Oregon_Trail_(series)
*/

package challenges_5_exceptions;

import java.util.Scanner;

public class TextBasedAdventureGame {
    public static void main(String[] args) {
        // an instance of TextBasedAdventureGame and calling the startGame method
        new TextBasedAdventureGame().startGame();
    }

    public void startGame() {
        // printing the title of the game
        System.out.println("Welcome to the Oregon Trail Adventure Game!");
        try (Scanner scanner = new Scanner(System.in)) {
            // first menu level with 5 different choices
            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Start a new adventure");
                System.out.println("2. Load saved game");
                System.out.println("3. View high scores");
                System.out.println("4. Help");
                System.out.println("5. Exit");

                if (!scanner.hasNextLine()) {
                    System.out.println("Input is closed. Exiting game.");
                    return;
                }

                String choice = scanner.nextLine().trim();

                // handling the user's choice and navigating to the second menu level or exiting the game
                switch (choice) {
                    case "1":
                        startNewAdventure(scanner);
                        break;
                    case "2":
                        loadSavedGame(scanner);
                        break;
                    case "3":
                        viewHighScores(scanner);
                        break;
                    case "4":
                        showHelp(scanner);
                        break;
                    case "5":
                        System.out.println("Thank you for playing! Goodbye!");
                        return; // exit the game
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        }
    }

    private void startNewAdventure(Scanner scanner) {
        System.out.println("\nStarting a new adventure...");
        showSecondLevelMenu(
                scanner,
                "Adventure Menu",
                "You will travel on the Oregon Trail. Take your things and choose your way.");
    }

    private void loadSavedGame(Scanner scanner) {
        System.out.println("\nLoading saved game...");
        showSecondLevelMenu(
                scanner,
                "Load Menu",
                "We are loading your last game. Get ready to continue.");
    }

    private void viewHighScores(Scanner scanner) {
        System.out.println("\nViewing high scores...");
        showSecondLevelMenu(
                scanner,
                "High Scores Menu",
                "These are the best scores from other players. Can you beat them?");
    }

    private void showHelp(Scanner scanner) {
        System.out.println("\nShowing help...");
        showSecondLevelMenu(
                scanner,
                "Help Menu",
                "In this game, your choices change what happens. Choose carefully and good luck!");
    }

    private void showSecondLevelMenu(Scanner scanner, String title, String text) {
        while (true) {
            System.out.println("\n" + title + ":");
            System.out.println(text);
            System.out.println("1. Continue");
            System.out.println("2. Check supplies");
            System.out.println("3. Rest");
            System.out.println("Q. Back to main menu");

            if (!scanner.hasNextLine()) {
                System.out.println("Input is closed. Exiting game.");
                return;
            }

            String action = scanner.nextLine().trim();

            if (action.equalsIgnoreCase("q")) {
                return;
            }

            switch (action) {
                case "1":
                    System.out.println("You continue your journey.");
                    break;
                case "2":
                    System.out.println("You check your supplies. Everything looks fine.");
                    break;
                case "3":
                    System.out.println("You take a short rest and feel better.");
                    break;
                default:
                    System.out.println("Use only numbers 1-3 or Q.");
            }
        }
    }
}
