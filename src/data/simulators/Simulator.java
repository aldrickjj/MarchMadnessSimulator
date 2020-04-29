package data.simulators;

import records.Match;
import records.Team;

public interface Simulator {
    public Team getWinner(Match match);
}
