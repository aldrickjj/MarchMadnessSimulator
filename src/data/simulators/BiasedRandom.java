package data.simulators;

import records.Match;
import records.Team;
import java.util.Random;

public class BiasedRandom implements Simulator{

    public BiasedRandom(){}
    
    @Override
    public Team getWinner(Team team1, Team team2) {
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
