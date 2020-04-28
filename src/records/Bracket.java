package records;

import java.util.HashMap;

public class Bracket {
    private HashMap<Integer, Match> matches;

    public Bracket(){
        this.matches = new HashMap<>();
    }

    public void addMatch(Match m){
        matches.put(m.getGameNum(), m);
    }

    public HashMap<Integer, Match> getBracket(){
        return matches;
    }
}
