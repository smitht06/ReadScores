import java.io.*;
import java.util.Arrays;

public class ReadFile {
    public static void main(String[] args){
        String [] master = new String[125];
        double [][] twoDMaster = new double[5][24];
        master = readFile();
        int h = 4;
        for(int i = 0; i < twoDMaster.length; i++){
            for (int j = 0; j < twoDMaster[0].length; j++){
                h++;
                twoDMaster[i][j]=Double.parseDouble(master[h]);
            }
        }
        for(int i = 0; i < twoDMaster.length; i++){
            System.out.println(Arrays.toString(twoDMaster[i]));
        }
        System.out.println(mean(twoDMaster[0]));
    }
    private static String[] readFile(){
        String line = "";
        BufferedReader file = null;
        String [] master = new String[125];
        int i = 0;
        try {

            file = new BufferedReader(new FileReader("Scores.txt"));
            while((line = file.readLine()) != null){
                master[i] = line;
                i++;
            }
        }catch(IOException e){
            System.out.println("Check file name, file not found");
        }
        return master;
    }
    private static double mean(double[] scores){
        double sum = 0;
        double mean;
        for(int i = 0; i < scores.length; i++){
            sum += scores[i];
        }
        mean = sum/scores.length;
        return mean;
    }

}
