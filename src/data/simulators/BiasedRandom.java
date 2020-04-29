package data.simulators;

import logging.Logger;
import records.Match;
import records.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BiasedRandom implements Simulator{

    private Map<String, Team> teamMap;
    private Logger logger;

    public BiasedRandom(List<Team> teamList) {
        logger = Logger.getInstance();
        teamMap = new HashMap<>();
        listToMap(teamList);
    }
    
    @Override
    public Team getWinner(Match match) {
        logger.info(this.getClass().getName(), "getWinner() called.");
        Team team1 = teamMap.get(match.getTeam1());
        Team team2 = teamMap.get(match.getTeam2());
        Team favorite = team1;
        Team underdog = team1;
        if(team2.getElo() > team1.getElo())
            favorite = team2;
        else
            underdog = team2;

        int dr = Math.abs(team1.getElo() - team2.getElo());
        int exp = (-1 * dr) / 400;
        double bottom = Math.pow(10, exp) + 1;
        double We = 1 / bottom;
        Random rand = new Random();
        double flip = rand.nextDouble();

        if(flip < We)
            return favorite;
        else
            return underdog;
    }

    private void listToMap(List<Team> teamList) {
        for(Team team : teamList) {
            String name = team.getName();
            teamMap.put(name, team);
        }
    }
}
