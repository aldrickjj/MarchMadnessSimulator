package data.simulators;

import records.Match;
import records.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChooseFavorite implements Simulator{
    private Team biased_team;
    private Map<String, Team> teamMap;

    public ChooseFavorite(List<Team> teamList, Team biased_team) {
        this.biased_team = biased_team;
        teamMap = new HashMap<>();
        listToMap(teamList);
    }

    @Override
    public Team getWinner(Match match) {
        Team team1 = teamMap.get(match.getTeam1());
        Team team2 = teamMap.get(match.getTeam2());
        if(team1.equals(biased_team) || team2.equals(biased_team)) {
            return biased_team;
        }
        else {
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
    }

    private void listToMap(List<Team> teamList) {
        for(Team team : teamList) {
            String name = team.getName();
            teamMap.put(name, team);
        }
    }
}
