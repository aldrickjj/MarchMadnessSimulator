package data.brackets;

public class BracketReaderFactory {

    public BracketReader getBracketReader(String filename) {
        if(filename.toLowerCase().contains("csv")) {
            return new BracketReaderCSV(filename);
        }
        else if(filename.toLowerCase().contains("json")) {
            return new BracketReaderJSON(filename);
        }
        else {
            return null;
        }
    }
}
