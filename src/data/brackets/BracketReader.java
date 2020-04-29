/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.brackets;

import records.Match;
import java.util.List;

/**
 * Interface for classes that read in bracket data
 */
public interface BracketReader {
    List<Match> getMatchList();
}
