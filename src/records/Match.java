package records;

public class Match {
    private int gameNum;
    private String team1;
    private String team2;
    private int goesTo;

    public Match(int gameNum, String team1, String team2, int goesTo){
        this.gameNum = gameNum;
        this.team1 = team1;
        this.team2 = team2;
        this.goesTo = goesTo;
    }

    public int getGameNum() {
        return gameNum;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getGoesTo() {
        return goesTo;
    }
}
