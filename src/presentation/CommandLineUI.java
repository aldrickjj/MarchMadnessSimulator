package presentation;

import controller.SimulatorController;
import records.Bracket;

import java.util.*;

public class CommandLineUI {
    private static final int NUM_STRATEGIES = 3;
    private Scanner in;
    private SimulatorController sc;

    public CommandLineUI(String team_filename, String bracket_filename) {
        in = new Scanner(System.in);
        sc = new SimulatorController(team_filename, bracket_filename);
    }

    public void start() {
        menu();
        sc.runSimulator();
        System.out.println(sc.getOutComeString());
    }

    private void menu() {
        printStrategies();
        int selection = getUserSelection();
        processSelection(selection);
    }

    private void printStrategies() {
        System.out.println("1. Favorite Always Wins");
        System.out.println("2. Biased Randomness");
        System.out.println("3. Pick a Favorite");
    }

    private int getUserSelection() {
        boolean hasValidSelection = false;
        int selection = -1;
        do {
            //remove whitespace around it
            String input = in.nextLine().trim();
            try {
                selection = Integer.parseInt(input);
                if (selection > 0 && selection <= NUM_STRATEGIES) {
                    hasValidSelection = true; //entered a valid number between 1 up to and including NUM_CHOICES
                } else {
                    System.out.println("User error: Must enter a number 1 to " + NUM_STRATEGIES + "! Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("User error: must enter a number! Try again!");
            }

        }
        while(!hasValidSelection); //keep going until the selection is valid
        return selection;
    }

    private void processSelection(int selection) {
        String teamname = "";
        switch (selection) {
            case 1:
                sc.setStrategy("FavoriteAlwaysWins", teamname);
                break;
            case 2:
                sc.setStrategy("BiasedRandomness", teamname);
                break;
            case 3:
                teamname = getBiasedTeamName();
                sc.setStrategy("PickAFavorite", teamname);
                break;
        }
    }

    private String getBiasedTeamName() {
        boolean validTeamName;
        String teamname;
        do{
            System.out.println("Please choose a valid team: ");
            teamname = in.nextLine().trim();
            validTeamName = sc.checkTeam(teamname);
            if(!validTeamName) {
                System.out.println("Invalid choice. Please try again. Remember to check for capitalization.");
            }
        }
        while(!validTeamName);
        return teamname;
    }
}
