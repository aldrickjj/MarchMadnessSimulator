package data.brackets;

import records.Match;
import java.util.List;

/**
 * Interface for classes that read in bracket data
 */
public interface BracketReader {
    List<Match> getMatchList();
}
