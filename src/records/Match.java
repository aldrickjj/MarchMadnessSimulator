package records;

/**
 * Class that holds the information for a Match
 */
public class Match {
    private int gameNum;
    private String team1;
    private String team2;
    private int goesTo;

    /**
     * Constructor for the Match
     * @param gameNum the number of the game in the bracket
     * @param team1 the name of the first team
     * @param team2 the name of the second team
     * @param goesTo the number of the game the winner goes to
     */
    public Match(int gameNum, String team1, String team2, int goesTo){
        this.gameNum = gameNum;
        this.team1 = team1;
        this.team2 = team2;
        this.goesTo = goesTo;
    }

    /**
     * Getter for the gameNum
     * @return the number of the match
     */
    public int getGameNum() {
        return gameNum;
    }

    /**
     * Getter for the first team in the match
     * @return the name of the team
     */
    public String getTeam1() {
        return team1;
    }

    /**
     * Getter for the second team in the match
     * @return the name of the second team
     */
    public String getTeam2() {
        return team2;
    }

    /**
     * Set the first team in the match
     * @param team1 the name of the team
     */
    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    /**
     * Set the second team in the match
     * @param team2 the name of the second team
     */
    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    /**
     * Getter for the next game num
     * @return the number of the next game
     */
    public int getGoesTo() {
        return goesTo;
    }

    /**
     * Compares if two Match objects are equal using gameNum and team names
     * @param o A Match object
     * @return true if all fields are equal, false otherwise
     */
    public boolean equals(Object o) {
        Match other = (Match) o;
        return this.gameNum==other.gameNum && this.team1.equals(other.team1) && this.team2.equals(other.team2) && this.goesTo==other.goesTo;
    }
}
