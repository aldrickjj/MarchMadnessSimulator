package controller;

import data.brackets.BracketReader;
import data.brackets.BracketReaderFactory;
import data.simulators.BiasedRandom;
import data.simulators.ChooseFavorite;
import data.simulators.FavoriteAlwaysWins;
import data.simulators.Simulator;
import data.teams.TeamReader;
import data.teams.TeamReaderFactory;
import logging.Logger;
import records.Bracket;
import records.Match;
import records.Team;

import java.util.List;

public class SimulatorController {
    private Logger logger;
    private Bracket bracket;
    private Simulator simulator;
    private TeamReader teamReader;
    private BracketReader bracketReader;
    private Team winner;

    public SimulatorController(String strategy, String team_filename, String bracket_filename) {
        logger = Logger.getInstance();
        TeamReaderFactory trf = new TeamReaderFactory();
        BracketReaderFactory brf = new BracketReaderFactory();
        teamReader = trf.getTeamReader(team_filename);
        bracketReader = brf.getBracketReader(bracket_filename);
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

    public SimulatorController(String strategy, String biased_team, String team_filename, String bracket_filename) {
        logger = Logger.getInstance();
        TeamReaderFactory trf = new TeamReaderFactory();
        BracketReaderFactory brf = new BracketReaderFactory();
        teamReader = trf.getTeamReader(team_filename);
        bracketReader = brf.getBracketReader(bracket_filename);
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
        List<Match> matches = bracketReader.getMatchList();
    }
}
