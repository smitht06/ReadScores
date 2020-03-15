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
        printTable(twoDMaster);
    }
    public static String[] readFile(){
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
    public static double mean(double[] scores){
        double sum = 0;
        double mean;
        for (double score : scores) {
            sum += score;
        }
        mean = sum/scores.length;
        return mean;
    }
    public static double standardDev(double mean, double[] scores){
        double summation = 0;
        for(double score : scores){
            summation += (score-mean) * (score-mean);
        }
        return Math.sqrt(summation/scores.length);
    }
    public static boolean meanDiffSignificance(double[] scores1, double[] scores2){
        double m1 = mean(scores1);
        double m2 = mean(scores2);
        double summation1 = 0;
        double summation2 = 0;
        double s2;
        double t;
        for(double score : scores1){
            summation1 += (score - m1) * (score - m1);
        }
        for (double score : scores2){
            summation2 += (score - m2) * (score - m2);
        }
        s2 = (summation1 + summation2)/(scores1.length+scores2.length-2);
        double denominator = Math.sqrt((s2/scores1.length)+(s2/scores2.length));
        t = (m1-m2)/denominator;
        return Math.abs(t) > 2.25;
    }

    public static void printTable(double[][] array){
        System.out.println("+--------+-----------+-----------+-----------+-----------+----------+");
        System.out.println("|Student | 6/12/2013 | 6/12/2014 | 6/12/2015 | 6/12/2016 | 6/12/2017");
        System.out.println("+========+===========+===========+===========+===========+==========+");
        for(int j = 0; j < array[0].length; j++){
            System.out.print("|");
            System.out.print(j+1);
            for(int i = 0; i < array.length; i++){
                System.out.print("\t\t | " + array[i][j]);
            }
                System.out.print("     |\n");

            }
        System.out.println("+========+===========+===========+===========+===========+==========+");
    }
}
