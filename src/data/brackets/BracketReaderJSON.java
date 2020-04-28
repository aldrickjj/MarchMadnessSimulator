package data.brackets;

import logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import records.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

public class BracketReaderJSON implements BracketReader {
    private List<Match> matches;
    private String filename;
    private String fileText;
    private Logger logger;

    public BracketReaderJSON(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    private void readFile() {
        fileText = readFileIntoString(filename);
        matches = new Vector<>();
        JSONArray root = new JSONArray(fileText);
        for(int i = 0; i < root.length(); i += 1) {
            matches.add(JSONToMatch(root.getJSONObject(i)));
        }
        logger.info(this.getClass().getName(), "content converts to list of matches.");
    }

    private Match JSONToMatch(JSONObject ob) {
        int gameNum = ob.getInt("Game No.");
        String team1 = ob.getString("Team 1");
        String team2 = ob.getString("Team 2");
        int goesTo = ob.getInt("Winner goes to game no.");
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

    @Override
    public List<Match> getMatchList() {
        if(matches == null) {
            readFile();
        }
        return matches;
    }
}
