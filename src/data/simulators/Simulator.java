package data.simulators;

import records.Team;

public interface Simulator {
    public Team getWinner(Team team1, Team team2);
}
