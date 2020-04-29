package data.brackets;

import logging.Logger;

/**
 * Creates a Bracket reader based on which file type is provided
 */
public class BracketReaderFactory {
    private Logger logger = Logger.getInstance();

    /**
     * Creates and returns the correct type of Bracket data reader
     * @param filename The name of the file in which the data is stored in
     * @return the Bracket data reader
     */
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
