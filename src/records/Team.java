/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package records;

/**
 * Object that holds the information of a team
 */
public class Team {
    private String name;
    private String region;
    private int elo;
    private int seed;

    /**
     * Constructor for the Team object
     * @param name the name of the team
     * @param elo the numerical strength of a team
     * @param region the region in which the team is in
     * @param seed the seeding of a team
     */
    public Team(String name, int elo, String region, int seed){
        this.name = name;
        this.elo = elo;
        this.region = region;
        this.seed = seed;
    }

    /**
     * Getter for the name of a Team
     * @return the name the team
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the region of a Team
     * @return the region of the team
     */
    public String getRegion() {
        return region;
    }

    /**
     * Getter for the ELO of a Team
     * @return the ELO
     */
    public int getElo() {
        return elo;
    }

    /**
     * Getter for the Seed of a Team
     * @return the Seed
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Compares if two Team objects are equal
     * @param o a Team object
     * @return true if all fields are equal, false otherwise
     */
    public boolean equals(Object o) {
        Team other = (Team) o;
        return this.name.equals(other.name) && this.region.equals(other.region) && this.elo==other.elo && this.seed==other.seed;
    }
}
