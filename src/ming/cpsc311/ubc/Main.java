import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    if (args.length != 1) {
	        throw new IllegalArgumentException("Only one argument can be passed in, a MING file");
        }

        try{
            //changed this because before input would be split on commas
            //buffered reader reads on each line, doesnt split on comma
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String readLine = "";
            //for writing to file
            FileWriter fw = new FileWriter("tmp.java");
            BufferedWriter writer = new BufferedWriter(fw);
            ArrayList<String> parsed = new ArrayList<String>();

            InputParser parser = new InputParser();

            while ((readLine = in.readLine()) != null) {
                //parsing then adding to 
                parsed.add(parser.parse(readLine));
            }

            for (String line: parsed){
                //writing to file
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
