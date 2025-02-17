/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.teams;

import java.util.List;
import records.Team;

/**
 * Interface for classes that will read in team data
 */
public interface TeamReader {
    public List<Team> getTeamList();
}
