package data.simulators;

import records.Team;

public class FavoriteAlwaysWins implements Simulator {
    public Team getWinner(Team team1, Team team2) {
        if(team1.getElo() > team2.getElo()) {
            return team1;
        }
        else if(team1.getElo() < team2.getElo()) {
            return team2;
        }
        else {
            int random_num = (int) (Math.random() * 100);
            if(random_num % 2 == 0) {
                return team2;
            }
            else {
                return team1;
            }
        }
    }
}
