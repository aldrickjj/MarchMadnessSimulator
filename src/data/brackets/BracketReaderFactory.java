package data.brackets;

import logging.Logger;

public class BracketReaderFactory {
    private Logger logger = Logger.getInstance();

    public BracketReader getBracketReader(String filename) {
        if(filename.toLowerCase().contains("csv")) {
            logger.info(this.getClass().getName(), "creating BracketReader for csv type file");
            return new BracketReaderCSV(filename);
        }
        else if(filename.toLowerCase().contains("json")) {
            logger.info(this.getClass().getName(), "creating BracketReader for json type file");
            return new BracketReaderJSON(filename);
        }
        else {
            logger.info(this.getClass().getName(), "cannot create BracketReader because of unknown file type");
            return null;
        }
    }
}
