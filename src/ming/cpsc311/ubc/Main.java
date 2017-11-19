import Exceptions.InvalidFunctionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {


    //error to handle: 
    // 1. invalid function                                          TODO
    // 2. invalid function format                                   TODO
    // 3. invalid number of arguments, in make too                  TODO
    // 4. multi function call on one line                           TODO
    // 5. invalid argument types                                    TODO
    // 6. uninstantiated variables                                  TODO
    // 7. naming variables funciton names eg bond = make('C')       TODO

    public static void main(String[] args) throws IOException, InvalidFunctionException, NoSuchMethodException {
	    if (args.length != 1) {
	        throw new IllegalArgumentException("Only one argument can be passed in, a MING file");
        }

        //changed this because before input would be split on commas
        //buffered reader reads on each line, doesnt split on comma
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String readLine;
        ArrayList<String> parsed = new ArrayList<>();
        InputParser parser = new InputParser();

        while ((readLine = in.readLine()) != null) {
            //parsing then adding to
            parsed.add(parser.parse(readLine.trim()));
        }

        //for writing to file (for demonstrating desugaring)
        ParsedMINGToJavaWriter writer = new ParsedMINGToJavaWriter("tmp.java");
        writer.write(parsed);

        // Interpretation:
        InputInterpreterInterface interpreter = new InputInterpreter();
        // "reflection" - https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
        interpreter.interpret(parsed);


        /*
        ArrayList<Structure> stuff = parser.getAll();

        for(Structure x: stuff){
            for(Bond y: x.getBonds()){
                System.out.println(y.getBondType());
                System.out.println(y.getStructure());
            }
        }
        */
    }
}
