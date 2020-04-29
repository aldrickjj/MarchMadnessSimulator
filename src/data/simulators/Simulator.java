/**
 * @authors Wei Wang (ww2ta), Aldrick Johan (aj2nud)
 */

package data.simulators;

import records.Match;
import records.Team;

/**
 * Interface for the Simulators
 */
public interface Simulator {
    Team getWinner(Match match);
}
