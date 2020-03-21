/*
 * File: ReadScores.java
 * Author: Anthony Smith
 * Date: 03/21/2020
 * Course: CO 5416
 * Purpose: This program reads scores from a file and outputs data to a table
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class ReadScores {
    //create decimal format for doubles
    private static DecimalFormat myDF = new DecimalFormat("###.#");
    public static void main(String[] args){
        //create 1d array and 2d array
        String [] master;
        double [][] twoDMaster = new double[5][24];
        //read file into 1d array
        master = readFile();
        int h = 4;
        //for loop to read scores into 2d array and convert to doubles
        for(int i = 0; i < twoDMaster.length; i++){
            for (int j = 0; j < twoDMaster[0].length; j++){
                h++;
                twoDMaster[i][j]=Double.parseDouble(master[h]);
            }
        }
        //output to console
        System.out.println("SUMMARY OF SCORES");
        printTable(twoDMaster);
        System.out.println();
        System.out.println("SIGNIFICANT DIFFERENCES IN MEAN SCORES");
        meanDiffTable(twoDMaster);
    }

    //this method contains all necessary statement to read the file
    public static String[] readFile(){
        //create string
        String line = "";
        //create buffferedreader
        BufferedReader file = null;
        //create array
        String [] master = new String[125];
        int i = 0;
        //read file into string and then into array
        try {
            file = new BufferedReader(new FileReader("Scores.txt"));
            while((line = file.readLine()) != null){
                master[i] = line;
                i++;
            }
            //catch IOException
        }catch(IOException e){
            System.out.println("Check file name, file not found");
        }
        //return the information from file into and array
        return master;
    }

    //this method calulates the mean of an array of doubles
    public static double mean(double[] scores){
        double sum = 0;
        double mean;
        for (double score : scores) {
            sum += score;
        }
        mean = sum/scores.length;
        return mean;
    }

    //this method calculated the standard deviation in an array of doubles
    public static double standardDev(double mean, double[] scores){
        double summation = 0;
        for(double score : scores){
            summation += (score-mean) * (score-mean);
        }
        return Math.sqrt(summation/scores.length);
    }

    //this method does a t test on the means of two arrays of doubles
    public static String meanDiffSignificance(double[] scores1, double[] scores2){
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
        //if t is more than 2.25 return Y otherwise N
        if(Math.abs(t) > 2.25){
            return "Y";
        }else{
            return "N";
        }
    }

    //method prints out formatted table
    public static void printTable(double[][] array){
        System.out.println("+--------+-----------+-----------+-----------+-----------+----------+");
        System.out.println("|Student | 6/12/2013 | 6/12/2014 | 6/12/2015 | 6/12/2016 | 6/12/2017");
        System.out.println("+========+===========+===========+===========+===========+==========+");
        for(int j = 0; j < array[0].length; j++){
            System.out.print("|");
            System.out.print(j+1);
            for (double[] scores : array) {
                System.out.print("\t\t | " + scores[j]);
            }
                System.out.print("     |\n");
            }
        System.out.println("+========+===========+===========+===========+===========+==========+");
        System.out.print("|Mean   ");
        for (double[] scores : array) {
            System.out.print(" |" + myDF.format(mean(scores)) + " \t\t");
        }
        System.out.print("|\n");
        System.out.print("|SD     ");
        for (double[] scores : array){
            System.out.print(" |" + myDF.format(standardDev(mean(scores),scores))+" \t\t");
        }
        System.out.print("|");
        System.out.println();
    }

    //method prints out mean difference table
    public static void meanDiffTable(double[][] array){
        int year = 2013;
        System.out.print("Date\t\t| 6/12/2013 | 6/12/2014 | 6/12/2015 | 6/12/2016 | 6/12/2017 |");
        for (int i = 0; i < array.length; i++){

            System.out.print("\n6/12/"+ year+" \t");
            year++;
            for (int j = 0; j < array.length; j++){
                if(j != i){
                System.out.print("| " + meanDiffSignificance(array[i],array[j]) + "\t\t\t");

            }else{
                    System.out.print("| n/a\t\t");
                }

            }

        }
    }


}
