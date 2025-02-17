/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package controller;

import data.brackets.*;
import data.simulators.*;
import data.teams.*;
import logging.Logger;
import records.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that acts as the controller of the simulator program
 */
public class SimulatorController {
    private Logger logger;
    private Bracket bracket;
    private TeamReader teamReader;
    private BracketReader bracketReader;
    private Map<Integer, Team> winnerMap;

    /**
     * Constructor for the Controller
     * @param team_filename the file name for the team data
     * @param bracket_filename the file  name for the bracket data
     */
    public SimulatorController(String team_filename, String bracket_filename) {
        logger = Logger.getInstance();
        logger.info(this.getClass().getName(), "creating a new instance of SimulatorController.");
        TeamReaderFactory trf = new TeamReaderFactory();
        BracketReaderFactory brf = new BracketReaderFactory();
        teamReader = trf.getTeamReader(team_filename);
        bracketReader = brf.getBracketReader(bracket_filename);
        winnerMap = new HashMap<>();
    }

    /**
     * Check if there is a team with the provided name
     * @param teamname the name of the team
     * @return true if the team exists, otherwise returns false
     */
    public boolean checkTeam(String teamname) {
        logger.info(this.getClass().getName(), "check if " + teamname + " is valid.");
        for(Team team : teamReader.getTeamList()) {
            if(team.getName().equals(teamname)) {
                logger.info(this.getClass().getName(), teamname + " is valid.");
                return true;
            }
        }
        logger.info(this.getClass().getName(), teamname + " is not valid.");
        return false;
    }

    /**
     * Starts running the simulator
     * @param simulator A simulator object based on which method of deciding the winner is used
     */
    public void runSimulator(Simulator simulator) {
        logger.info(this.getClass().getName(), "start running the simulator.");
        HashMap<Integer, Match> matchMap = listToBracket();
        List<Match> matches = bracketReader.getMatchList().stream()
                .sorted(Comparator.comparingInt(Match::getGameNum))
                .collect(Collectors.toList());
        for(Match match : matches) {
            logger.info(this.getClass().getName(), "simulating match " + match.getGameNum());
            Team winningTeam = simulator.getWinner(match);
            logger.info(this.getClass().getName(), "winner is " + winningTeam.getName());
            winnerMap.put(match.getGameNum(), winningTeam);
            int goesTo = match.getGoesTo();
            if(goesTo == 0) {
                break;
            }
            if(matchMap.get(goesTo).getTeam2().equals("")) {
                matchMap.get(goesTo).setTeam2(winningTeam.getName());
            }
            else {
                matchMap.get(goesTo).setTeam1(winningTeam.getName());
            }
        }
        logger.info(this.getClass().getName(), "complete running the simulator. Winner should be available");
    }

    /**
     * Produces the String that will be output
     * @return the String that will be output
     */
    public String getOutComeString() {
        logger.info(this.getClass().getName(), "producing the outcome of the simulation into a String");
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < winnerMap.size(); i += 1) {
            sb.append("Match ").append(i).append(" winner: ").append(winnerMap.get(i).getName()).append(". Advance to match ")
                    .append(bracket.getMatch(i).getGoesTo()).append("\n");
        }
        sb.append("Match ").append(67).append(" winner: ").append(winnerMap.get(67).getName()).append(".");
        return sb.toString();
    }

    /**
     * Adds a list of matches to the bracket
     * @return the HashMap that represents the bracket
     */
    private HashMap<Integer, Match> listToBracket() {
        logger.info(this.getClass().getName(), "add a list of matches to the bracket");
        bracket = new Bracket();
        List<Match> matches = bracketReader.getMatchList();
        for(Match match : matches) {
            bracket.addMatch(match);
        }
        logger.info(this.getClass().getName(), "finished adding");
        return bracket.getBracket();
    }

    /**
     * get the list of teams
     * @return the list of teams
     */
    public List<Team> getTeamList() {
        return teamReader.getTeamList();
    }
}
