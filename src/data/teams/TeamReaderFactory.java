package data.teams;

import data.brackets.BracketReaderCSV;
import data.brackets.BracketReaderJSON;
import logging.Logger;

public class TeamReaderFactory {
    private Logger logger = Logger.getInstance();

    public TeamReader getTeamReader(String filename) {
        if(filename.toLowerCase().contains("csv")) {
            logger.info(this.getClass().getName(), "creating TeamReader for csv type file");
            return new TeamReaderCSV(filename);
        }
        else if(filename.toLowerCase().contains("json")) {
            logger.info(this.getClass().getName(), "creating TeamReader for json type file");
            return new TeamReaderJSON(filename);
        }
        else {
            logger.info(this.getClass().getName(), "cannot create TeamReader because of unknown file type");
            return null;
        }
    }
}
