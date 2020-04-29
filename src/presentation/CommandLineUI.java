package presentation;

import controller.SimulatorController;
import data.simulators.BiasedRandom;
import data.simulators.ChooseFavorite;
import data.simulators.FavoriteAlwaysWins;
import data.simulators.Simulator;
import logging.Logger;
import records.Team;

import java.util.*;

public class CommandLineUI {
    private static final int NUM_STRATEGIES = 3;
    private Scanner in;
    private SimulatorController sc;
    private Logger logger;
    private List<Team> teamList;
    private Simulator simulator;

    public CommandLineUI(String team_filename, String bracket_filename) {
        logger = Logger.getInstance();
        logger.info(this.getClass().getName(), "creating a new instance of CommandLineUI.");
        in = new Scanner(System.in);
        sc = new SimulatorController(team_filename, bracket_filename);
        teamList = sc.getTeamList();
    }

    public void start() {
        menu();
        sc.runSimulator(simulator);
        System.out.println(sc.getOutComeString());
    }

    private void menu() {
        printStrategies();
        int selection = getUserSelection();
        processSelection(selection);
    }

    private void printStrategies() {
        logger.info(this.getClass().getName(), "printing the list of strategies to the terminal");
        System.out.println("1. Favorite Always Wins");
        System.out.println("2. Biased Randomness");
        System.out.println("3. Pick a Favorite");
    }

    private int getUserSelection() {
        logger.info(this.getClass().getName(), "getting user choice for strategy");
        boolean hasValidSelection = false;
        int selection = -1;
        do {
            //remove whitespace around it
            String input = in.nextLine().trim();
            try {
                selection = Integer.parseInt(input);
                logger.info(this.getClass().getName(), "user selected " + selection);
                if (selection > 0 && selection <= NUM_STRATEGIES) {
                    hasValidSelection = true; //entered a valid number between 1 up to and including NUM_CHOICES
                } else {
                    logger.error(this.getClass().getName(), "User error: Must enter a number 1 to " + NUM_STRATEGIES + "! Try again!");
                    System.out.println("User error: Must enter a number 1 to " + NUM_STRATEGIES + "! Try again!");
                }
            } catch (NumberFormatException e) {
                logger.error(this.getClass().getName(), "User error: must enter a number! Try again!");
                System.out.println("User error: must enter a number! Try again!");
            }

        }
        while(!hasValidSelection); //keep going until the selection is valid
        logger.info(this.getClass().getName(), "selection set");
        return selection;
    }

    private void processSelection(int selection) {
        logger.info(this.getClass().getName(), "processing user's selection.");
        String teamname = "";
        switch (selection) {
            case 1:
                simulator = new FavoriteAlwaysWins(teamList);
                logger.info(this.getClass().getName(), "simulator created using the FavoriteAlwaysWins strategy");
                break;
            case 2:
                simulator = new BiasedRandom(teamList);
                logger.info(this.getClass().getName(), "simulator created using the BiasedRandomness strategy");
                break;
            case 3:
                teamname = getBiasedTeamName();
                simulator = new ChooseFavorite(teamList, teamname);
                logger.info(this.getClass().getName(), "simulator created using the PickAFavorite strategy");
                break;
        }
    }

    private String getBiasedTeamName() {
        logger.info(this.getClass().getName(), "getting user's choice for biased team.");
        boolean validTeamName;
        String teamname;
        do{
            System.out.println("Please choose a valid team: ");
            teamname = in.nextLine().trim();
            logger.info(this.getClass().getName(), "user picked " + teamname);
            validTeamName = sc.checkTeam(teamname);
            if(!validTeamName) {
                logger.info(this.getClass().getName(), "Invalid choice. Please try again. Remember to check for capitalization.");
                System.out.println("Invalid choice. Please try again. Remember to check for capitalization.");
            }
        }
        while(!validTeamName);
        logger.info(this.getClass().getName(), "biased team name set.");
        return teamname;
    }
}
