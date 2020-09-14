MarchMadnessSimulator by Aldrick Johan(aj2nud) and Wei Wang(ww2ta)
Simulates March Madness using various metrics, explained further below.

**Packages and Classes in this File:**

controller - contains the controller layer of the program

    SimulatorController - controller for the program, goes in between 
    the data layer and the view layer 

data - contains the data layer of the program

    brackets - deals with reading in bracket data
        BracketReader - interface defining what a BracketReader must do
        BracketReaderCSV - a BracketReader that reads from CSV Files
        BracketReaderFactory - Creates BracketReader based on input file
        BracketReaderJSON - a BracketReader that reads from JSON Files
    
    simulators - contains strategies that are used to choose the winners
        BiasedRandom - chooses team using a formula that is partially random
        ChooseFavorite - allows the user to choose a team that always win
        FavoriteAlwaysWins - the winner always wins
        Simulator - interface defining a simuator
    
    teams
        TeamReader - interface defining what a TeamReader must do
        TeamReaderCSV - a TeamReader that reads from CSV Files
        TeamReaderFactory - Creates TeamReader based on input file
        TeamReaderJSON - a TeamReader that reads from JSON Files

logging - contains the logging system for the program 

    Logger - implements a singleton logger that writes updates to a txt file

presentation - contains the view layer of the project

    CommandLineUI - provides the user interface of the program

records - Plain Old Java Objects

    Bracket - holds bracket information
    Match - holds match information
    Team - holds team information

start - starts the program


**How to run:** 

    Open project in Intellij and run the program with two command line arguments for the file names.
    Provide the team file before providing the bracket file. No additional libraries are required.
    Example: java start teams.csv bracket.csv
