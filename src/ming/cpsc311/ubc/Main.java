package ming.cpsc311.ubc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    if (args.length != 1) {
	        throw new IllegalArgumentException("Only one argument can be passed in, a MING file");
        }
        Scanner in = new Scanner(new FileReader(args[0]));
        while (in.hasNext()) {
            // TODO: This is just to demonstrate that it can read a file in and print it out.
            System.out.println(in.next());
        }
        InputParser parser = new InputParser();
    }
}
