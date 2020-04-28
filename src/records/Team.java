package records;

public class Team {
    private String name;
    private String region;
    private int elo;
    private int seed;

    public Team(String name, int elo, String region, int seed){
        this.name = name;
        this.elo = elo;
        this.region = region;
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public int getElo() {
        return elo;
    }

    public int getSeed() {
        return seed;
    }
}
