import presentation.CommandLineUI;

public class start {

    public static void main(String[] args){
        if(args.length < 2) {
            System.out.println("You must provide exactly two command line arguments.");
            System.exit(0);
        }
        String team_file_name = args[0];
        String bracket_file_name = args[1];
        CommandLineUI clu = new CommandLineUI(team_file_name, bracket_file_name);
        clu.start();
    }
}
