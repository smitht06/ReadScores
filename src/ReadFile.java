import java.io.*;
import java.util.*;

public class ReadFile {
    public static void main(String[] args){
        String [] master = new String[200];

    }

    private static void readFile() throws FileNotFoundException {
        try {
            BufferedReader read = null;
            read = new BufferedReader(new FileReader("scores.txt"));
        }catch(FileNotFoundException e){
            System.out.println("Check file name, file not found");
        }
    }
}
