package data.teams;

import logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import records.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

/**
 * Class that reads Team data from a JSON
 */
public class TeamReaderJSON implements TeamReader{
    private String filename;
    private String fileText;
    private List<Team> teamList;
    private Logger logger;

    /**
     * Constructor for the JSON Reader
     * @param filename name of the input file
     */
    public TeamReaderJSON(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    /**
     * Reads from the provided file
     */
    private void readFile() {
        teamList = new Vector<>();
        fileText = readFileIntoString(filename);
        JSONArray root = new JSONArray(fileText);
        for(int i = 0; i < root.length(); i += 1) {
            teamList.add(JSONToTeam(root.getJSONObject(i)));
        }
        logger.info(this.getClass().getName(), "content converts to list of teams.");
    }

    /**
     * Extract the contents from a json file and store them as a String
     * @param filename the name of the json file
     * @return a String that contains information in the json file
     */
    private String readFileIntoString(String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            logger.info(this.getClass().getName(), "Content successfully read from the JSON file.");
            return sb.toString();
        } catch (Exception e) {
            logger.error(this.getClass().getName(), "Error occurred when reading " + filename + " file.");
            return null;
        }
    }

    /**
     * Converts a JSON Object into a Team object
     * @param ob the JSON Object
     * @return the Team object created from the data in the JSON Object
     */
    private Team JSONToTeam(JSONObject ob) {
        String name = ob.getString("Team Name");
        int elo = ob.getInt("Elo");
        String region = ob.getString("Region");
        int seed = ob.getInt("Seed");
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
