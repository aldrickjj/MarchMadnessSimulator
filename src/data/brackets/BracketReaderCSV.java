package data.brackets;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import records.Match;
import java.io.BufferedReader;
import java.util.stream.Collectors;

import logging.Logger;

/**
 * Class that reads in bracket data from a csv file
 */
public class BracketReaderCSV implements BracketReader {
    private List<Match> matches;
    private String filename;
    private Logger logger;

    /**
     * Constructor for the CSV reader
     * @param filename The name of the file that will be read from
     */
    public BracketReaderCSV(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    /**
     * Reads from the file
     */
    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            br.readLine();
            matches = br.lines()
                    .map(line -> lineToMath(line))
                    .collect(Collectors.toList());
            logger.info(this.getClass().getName(), "bracket file content successfully read.");
        } catch (IOException e) {
            logger.error(this.getClass().getName(), "error with read bracket file content.");
        }
    }

    /**
     * Convert a line in the CSV file into a Match object
     * @param line a line from the CSV file
     * @return a Match object
     */
    private Match lineToMath(String line) {
        String[] elements = line.split(",");
        int gameNum = Integer.parseInt(elements[0].trim());
        if(elements.length == 1) {
            return new Match(gameNum, "", "", 0);
        }
        String team1 = elements[1].trim();
        String team2 = elements[2].trim();
        int goesTo = Integer.parseInt(elements[3].trim());
        return new Match(gameNum, team1, team2, goesTo);
    }

    /**
     * Getter for the list of Matches
     * @return the list of Matches
     */
    @Override
    public List<Match> getMatchList() {
        if(matches == null) {
            readFile();
        }
        return matches;
    }
}
