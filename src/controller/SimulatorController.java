package controller;

import data.brackets.*;
import data.simulators.*;
import data.teams.*;
import logging.Logger;
import records.*;

import java.util.*;
import java.util.stream.Collectors;

public class SimulatorController {
    private Logger logger;
    private Bracket bracket;
    private Simulator simulator;
    private TeamReader teamReader;
    private BracketReader bracketReader;
    private Team winner;

    public SimulatorController(String team_filename, String bracket_filename) {
        logger = Logger.getInstance();
        TeamReaderFactory trf = new TeamReaderFactory();
        BracketReaderFactory brf = new BracketReaderFactory();
        teamReader = trf.getTeamReader(team_filename);
        bracketReader = brf.getBracketReader(bracket_filename);
    }

    public boolean checkTeam(String teamname) {
        for(Team team : teamReader.getTeamList()) {
            if(team.getName().equals(teamname)) {
                return true;
            }
        }
        return false;
    }

    public void setStrategy(String strategy) {
        if(strategy.equals("FavoriteAlwaysWins")) {
            simulator = new FavoriteAlwaysWins(teamReader.getTeamList());
            logger.info(this.getClass().getName(), "simulator created using the FavoriteAlwaysWins strategy");
        }
        else if(strategy.equals("BiasedRandomness")) {
            simulator = new BiasedRandom(teamReader.getTeamList());
            logger.info(this.getClass().getName(), "simulator created using the BiasedRandomness strategy");
        }
        else {
            simulator = null;
            logger.info(this.getClass().getName(), "simulator cannot be created");
        }
    }

    public void setStrategy(String strategy, String biased_team) {
        if(strategy.equals("PickAFavorite")) {
            simulator = new ChooseFavorite(teamReader.getTeamList(), biased_team);
            logger.info(this.getClass().getName(), "simulator created using the PickAFavorite strategy");
        }
        else {
            simulator = null;
            logger.info(this.getClass().getName(), "simulator cannot be created");
        }
    }

    public void runSimulator() {
        HashMap<Integer, Match> matchMap = listToBracket();
        List<Match> matches = bracketReader.getMatchList().stream()
                .sorted(Comparator.comparingInt(Match::getGameNum))
                .collect(Collectors.toList());
        for(Match match : matches) {
            Team winningTeam = simulator.getWinner(match);
            int goesTo = match.getGoesTo();
            if(goesTo == 0) {
                this.winner = winningTeam;
                break;
            }
            Match nextMatch = matchMap.get(goesTo);
            if(nextMatch.getTeam1().equals("")) {
                nextMatch.setTeam2(winningTeam.getName());
            }
            else {
                nextMatch.setTeam1(winningTeam.getName());
            }
        }
        logger.info(this.getClass().getName(), "complete running the simulator. Winner should be available");
    }

    public Team getWinner() {
        logger.info(this.getClass().getName(), "getWinner() called.");
        return this.winner;
    }

    private HashMap<Integer, Match> listToBracket() {
        bracket = new Bracket();
        List<Match> matches = bracketReader.getMatchList();
        for(Match match : matches) {
            bracket.addMatch(match);
        }
        return bracket.getBracket();
    }
}
