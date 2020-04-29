package data.brackets;

import logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import records.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

/**
 * Class that reads bracket data from a JSON file
 */
public class BracketReaderJSON implements BracketReader {
    private List<Match> matches;
    private String filename;
    private String fileText;
    private Logger logger;

    /**
     * Constructor for the Bracket reader
     * @param filename the name of the file with bracket data
     */
    public BracketReaderJSON(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    /**
     * Reads the file that is provided
     */
    private void readFile() {
        fileText = readFileIntoString(filename);
        matches = new Vector<>();
        JSONArray root = new JSONArray(fileText);
        for(int i = 0; i < root.length(); i += 1) {
            matches.add(JSONToMatch(root.getJSONObject(i)));
        }
        logger.info(this.getClass().getName(), "content converts to list of matches.");
    }

    /**
     * Converts a JSON Object to a Match object
     * @param ob JSON Object that is read in
     * @return a Match object formed with the data in the JSON Object
     */
    private Match JSONToMatch(JSONObject ob) {
        int gameNum = ob.getInt("Game No.");
        String team1 = ob.getString("Team 1");
        String team2 = ob.getString("Team 2");
        int goesTo;
        try {
            goesTo = ob.getInt("Winner goes to game no.");
        }
        catch (Exception e) {
            goesTo = 0;
        }
        return new Match(gameNum, team1, team2, goesTo);
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
     * Gets the list of matches produced
     * @return the list of matches
     */
    @Override
    public List<Match> getMatchList() {
        if(matches == null) {
            readFile();
        }
        return matches;
    }
}
