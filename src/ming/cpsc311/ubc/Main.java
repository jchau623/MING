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
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp.java"));
            //writer.write(str);
            ArrayList parsed = new ArrayList<String>();

            InputParser parser = new InputParser();

            while ((readLine = in.readLine()) != null) {
                parsed.add(parser.parse(readLine));
            }

            for (String line: parsed){
                System.out.println(parsed);
                writer.write(parsed);
                writer.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
