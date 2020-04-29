package records;

import java.util.HashMap;

/**
 * Object that holds bracket information
 */
public class Bracket {
    private HashMap<Integer, Match> matches;

    /**
     * Constructor that creates an empty bracket
     */
    public Bracket(){
        this.matches = new HashMap<>();
    }

    /**
     * Add a match to the bracket
     * @param m the Match that will be added
     */
    public void addMatch(Match m){
        matches.put(m.getGameNum(), m);
    }

    /**
     * Get a Match from the bracket
     * @param gameNum the Match number (identifier)
     * @return the Match
     */
    public Match getMatch(int gameNum) {
        return matches.get(gameNum);
    }

    /**
     * Getter for the Bracket
     * @return the HashMap that represents the Bracket
     */
    public HashMap<Integer, Match> getBracket(){
        return matches;
    }
}
