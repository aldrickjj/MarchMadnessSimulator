package data.teams;

import logging.Logger;
import records.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TeamReaderCSV implements TeamReader{
    private List<Team> teamList;
    private String filename;
    private Logger logger;

    public TeamReaderCSV(String filename) {
        this.filename = filename;
        logger = Logger.getInstance();
    }

    private void readFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            teamList = br.lines()
                    .filter(line -> !line.equals("Team Name,Elo,Region,Seed"))
                    .map(line -> lineToTeam(line))
                    .collect(Collectors.toList());
            logger.info(this.getClass().getName(), "contents in the team file successfully read and converted to team objects");
        }
        catch (IOException e) {
            logger.error(this.getClass().getName(), "fail to read the team file content");
        }
    }

    private Team lineToTeam(String line) {
        String[] elements = line.split(",");
        String name = elements[0].trim();
        int elo = Integer.parseInt(elements[1].trim());
        String region = elements[2].trim();
        int seed = Integer.parseInt(elements[3].trim());
        return new Team(name, elo, region, seed);
    }

    @Override
    public List<Team> getTeamList() {
        if(teamList == null) {
            readFile();
        }
        return teamList;
    }
}
