/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.teams;

import logging.Logger;

/**
 * Creates readers for the Team data based on file type
 */
public class TeamReaderFactory {
    private Logger logger = Logger.getInstance();

    /**
     * Creates the correct type of TeamReader
     * @param filename name of the input file
     * @return the corresponding TeamReader based on the file type
     */
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
