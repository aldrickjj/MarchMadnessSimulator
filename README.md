MarchMadnessSimulator by Aldrick Johan(aj2nud) and Wei Wang(ww2ta)

**Packages and Classes in this File:**

controller - has the controller layer of the program

    SimulatorController - controller for the program

data - has the data layer of the program

    brackets - deals with reading in bracket data
        BracketReader - interface defining what a BracketReader must do
        BracketReaderCSV - a BracketReader that reads from CSV Files
        BracketReaderFactory - Creates BracketReader based on input file
        BracketReaderJSON - a BracketReader that reads from JSON Files
    
    simulators - deals with the strategies that are used to choose the winners
        BiasedRandom - chooses team using a formula that is partially random
        ChooseFavorite - allows the user to choose a team that always win
        FavoriteAlwaysWins - the winner always wins
        Simulator - interface defining a simuator
    
    teams
        TeamReader - interface defining what a TeamReader must do
        TeamReaderCSV - a TeamReader that reads from CSV Files
        TeamReaderFactory - Creates TeamReader based on input file
        TeamReaderJSON - a TeamReader that reads from JSON Files

logging - has a logger 

    Logger - implements a singleton logger

presentation - has the view layer of the project

    CommandLineUI - UI for the program

records - Plain Old Java Objects

    Bracket - holds bracket information
    Match - holds match information
    Team - holds team information

start - starts the program


**How to run:** 

    Open project in Intellij and run the program with two command line arguments for the file names.
    No additional libraries are required.
