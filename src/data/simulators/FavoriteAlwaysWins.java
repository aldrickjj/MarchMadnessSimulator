package data.simulators;

import records.Match;
import records.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FavoriteAlwaysWins implements Simulator {

    private Map<String, Team> teamMap;

    public FavoriteAlwaysWins(List<Team> teamList) {
        teamMap = new HashMap<>();
        listToMap(teamList);
    }

    @Override
    public Team getWinner(Match match) {
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

    private void listToMap(List<Team> teamList) {
        for(Team team : teamList) {
            String name = team.getName();
            teamMap.put(name, team);
        }
    }
}
