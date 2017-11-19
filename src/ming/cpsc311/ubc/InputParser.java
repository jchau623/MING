import Exceptions.InvalidFunctionException;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class InputParser {
    //keep a list of all instantiated varialbes
    //error checking later in parseFunction will use this to see if a variable is instantiated statically
    private ArrayList<Structure> elements = new ArrayList<>();

    String parse(String s) throws InvalidFunctionException {
        String func;
        if(s.contains("=")){
            //variable declaration
            String[] parts = s.split("=");
            //adding to list of instantiated varaibles 
            String variableName = sanitize(parts[0]);
            Structure struc = new Structure(variableName, parseFunction(sanitize(parts[1])));
            elements.add(struc);
            func = "MING " + variableName + " = " + parseFunction(sanitize(parts[1]));
        } else {
            //not variable declaration, just function application
            func = parseFunction(s);
        }
        return func;
    }

    private String parseFunction(String s) throws InvalidFunctionException {
        String func;
        String[] function = s.split("\\(");
        if(function.length != 2) throw new InvalidFunctionException("Invalid function form");
        String functionName = function[0];
        String[] args  = function[1].split("\\)");
        if (args.length != 1 && args[0].equals(function[1])) throw new InvalidFunctionException("Invalid function form");
        args = Arrays.stream(args[0].split(",")).map(String::trim).toArray(String[]::new); //trims whitespace

        switch(functionName){
            case "make-ring":           func = s;
                                        break;
            // Making a linear molecular chain structure
            case "make-chain":          func = s;
                                        break;
            case "single-bond":         if (args.length != 2) throw new InvalidFunctionException("single-bond requires 2 arguments, " +
                                        "given " + args.length);
                                        func = "bond(" + args[0] + ", " + args[1] + ", 1);";
                                        break;
            case "double-bond":         if (args.length != 2) throw new InvalidFunctionException("double-bond requires 2 arguments, " +
                                        "given " + args.length);
                                        func = "bond(" + args[0] + ", " + args[1] + ", 2);";
                                        break;
            case "triple-bond":         if (args.length != 2) throw new InvalidFunctionException("triple-bond requires 2 arguments, " +
                                        "given " + args.length);
                                        func = "bond(" + args[0] + ", " + args[1] + ", 3);";
                                        break;
            case "in-bond":             if (args.length != 2) throw new InvalidFunctionException("in-bond requires 2 arguments, " +
                                        "given " + args.length);
                                        func = "bond(" + args[0] + ", " + args[1] + ", 4);";
                                        break;
            case "out-bond":            if (args.length != 2) throw new InvalidFunctionException("out-bond requires 2 arguments, " +
                                        "given " + args.length);
                                        func = "bond(" + args[0] + ", " + args[1] + ", 5);";
                                        break;
            case "mirror":              func = s;
                                        break;
            case "conver-to-smiles":    func = s;
                                        break;
            case "check-validity":      func = s;
                                        break;
            case "render":              func = s;
                                        break;
            // Making a single-element molecular structure
            case "make":                func = "make(" + s.substring(s.indexOf("(") + 1, s.indexOf(")")) + ")";
                                        break;
            //error because what is being use isn't in our language
            default:                    func = "ERROR"; //TODO: should throw exception? or ignore "ERROR"
                                        break;
        }
        return func;
    }


    //get rids of spaces in a string
    private String sanitize(String s){
        return s.replaceAll("\\s+","");
    }

    public Structure getVariable(String vName){
        String name = sanitize(vName);
        for(Structure s: elements){
            if(s.getVname().equals(name)){
                return s;
            }
        }
        //to do throw undefined variable error
        return null;
    }

    //for testing purposes only
    ArrayList<Structure> getAll(){
        return this.elements;
    }
}

