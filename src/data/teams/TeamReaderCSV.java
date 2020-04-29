/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.teams;

import logging.Logger;
import records.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that reads in Team data from a CSV file
 */
public class TeamReaderCSV implements TeamReader{
    private List<Team> teamList;
    private String filename;
    private Logger logger;

    /**
     * Constructor for the CSV reader
     * @param filename the name of the file to read from
     */
    public TeamReaderCSV(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    /**
     * Read the given file
     */
    private void readFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            br.readLine();
            teamList = br.lines()
                    .map(line -> lineToTeam(line))
                    .collect(Collectors.toList());
            logger.info(this.getClass().getName(), "contents in the team file successfully read and converted to team objects");
        }
        catch (IOException e) {
            logger.error(this.getClass().getName(), "fail to read the team file content");
        }
    }

    /**
     * Convert a given String into a Team object
     * @param line the String with the Team information
     * @return the Team object
     */
    private Team lineToTeam(String line) {
        String[] elements = line.split(",");
        String name = elements[0].trim();
        int elo = Integer.parseInt(elements[1].trim());
        String region = elements[2].trim();
        int seed = Integer.parseInt(elements[3].trim());
        return new Team(name, elo, region, seed);
    }

    /**
     * Getter for the list of teams
     * @return the list of teams
     */
    @Override
    public List<Team> getTeamList() {
        if(teamList == null) {
            readFile();
        }
        return teamList;
    }
}
