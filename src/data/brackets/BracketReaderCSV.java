package data.brackets;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import records.Match;
import java.io.BufferedReader;
import java.util.Vector;
import java.util.stream.Collectors;

import logging.Logger;

public class BracketReaderCSV implements BracketReader {
    private List<Match> matches;
    private String filename;
    private Logger logger;

    public BracketReaderCSV(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            matches = br.lines()
                    .filter(line -> !line.equals("Game No.,Team 1,Team 2,Winner goes to game no."))
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
        String team1 = elements[1].trim();
        String team2 = elements[2].trim();
        int goesTo;
        if(elements[3].trim().equals("")){
            goesTo = 0;
        }
        else {
            goesTo = Integer.parseInt(elements[3].trim());
        }
        return new Match(gameNum, team1, team2, goesTo);
    }

    @Override
    public List<Match> getMatchList() {
        if(matches == null) {
            readFile();
        }
        return matches;
    }
}
