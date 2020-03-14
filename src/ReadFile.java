import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class ReadFile {
    private static DecimalFormat myDF = new DecimalFormat("###.###");
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
        for (double[] doubles : twoDMaster) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println(mean(twoDMaster[0]));
        System.out.println(standardDev(mean(twoDMaster[1]),twoDMaster[1]));
        System.out.println(meanDiffSignificance(twoDMaster[2],twoDMaster[3]));
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
        for (double score : scores) {
            sum += score;
        }
        mean = sum/scores.length;
        return mean;
    }
    private static double standardDev(double mean, double[] scores){
        double summation = 0;
        for(double score : scores){
            summation += (score-mean) * (score-mean);
        }
        double stdDeviation = Math.sqrt(summation/scores.length);
        return stdDeviation;
    }
    private static boolean meanDiffSignificance(double[] scores1, double[] scores2){
        double m1 = mean(scores1);
        double m2 = mean(scores2);
        double sumation1 = 0;
        double sumation2 = 0;
        double s2 = 0;
        double t = 0;
        for(double score : scores1){
            sumation1 += (score - m1) * (score - m1);
        }
        for (double score : scores2){
            sumation2 += (score - m2) * (score - m2);
        }
        s2 = (sumation1 + sumation2)/(scores1.length+scores2.length);
        double denominator = Math.sqrt((s2/scores1.length)+(s2/scores2.length));
        t = (m1-m2)/denominator;
        System.out.println(myDF.format(t));
        System.out.println(t);
        return Math.abs(t) > 2.25;
    }

}
