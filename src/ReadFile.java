import java.io.*;

public class ReadFile {
    public static void main(String[] args){
        String [] master = new String[125];
        //String [][] twoDMaster = new String[][];
        master = readFile();
        for(int i =0; i < master.length; i++){
            System.out.println(master[i]);
        }

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

}
