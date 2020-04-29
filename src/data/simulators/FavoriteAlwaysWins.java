/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.simulators;

import logging.Logger;
import records.Match;
import records.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implements a simulator where the favorite in each Match always wins
 */
public class FavoriteAlwaysWins implements Simulator {

    private Map<String, Team> teamMap;
    private Logger logger;

    /**
     * Constructor for the simulator
     * @param teamList the list of teams
     */
    public FavoriteAlwaysWins(List<Team> teamList) {
        logger = Logger.getInstance();
        teamMap = new HashMap<>();
        listToMap(teamList);
    }

    /**
     * Returns the winner of a Match by comparing the ELOs of the participating teams
     * @param match the Match in which the winner will be decided
     * @return the Team with the higher ELO
     */
    @Override
    public Team getWinner(Match match) {
        logger.info(this.getClass().getName(), "getWinner() called.");
        Team team1 = teamMap.get(match.getTeam1());
        Team team2 = teamMap.get(match.getTeam2());
        if(team1.getElo() > team2.getElo()) {
            return team1;
        }
        else if(team1.getElo() < team2.getElo()) {
            return team2;
        }
        else {
            Random rand = new Random();
            int random_int = rand.nextInt();
            if(random_int % 2 == 0) {
                return team2;
            }
            else {
                return team1;
            }
        }
    }

    /**
     * Converts the list of teams to a Hashmap
     * @param teamList the list of teams
     */
    private void listToMap(List<Team> teamList) {
        for(Team team : teamList) {
            String name = team.getName();
            teamMap.put(name, team);
        }
    }
}
