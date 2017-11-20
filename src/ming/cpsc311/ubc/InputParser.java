package ming.cpsc311.ubc;

import Exceptions.InvalidFunctionException;

import java.util.Arrays;
import java.util.ArrayList;

class InputParser {
    //keep a list of all instantiated varialbes
    //error checking later in parseFunction will use this to see if a variable is instantiated statically

    // private ArrayList<Structure> elements = new ArrayList<>();

    // Global variables are cheating, I'm sorry! But it's pretty late..
    private String variableName;
    String parse(String s) throws InvalidFunctionException {
        String func;
        InputInterpreterInterface interpreter = new InputInterpreter();
        if(s.contains("=")){
            //variable declaration
            String[] parts = s.split("=");
            //adding to list of instantiated variables
            this.variableName = sanitize(parts[0]);
            //Structure struc = new Structure(variableName, parseFunction(sanitize(parts[1])));
            //elements.add(struc);
            func = "Structure " + this.variableName + " = " + parseFunction(sanitize(parts[1]));
        } else {
            //not variable declaration, just function application
            this.variableName = null;
            func = parseFunction(s);
        }
        return func;
    }

    private String parseFunction(String s) throws InvalidFunctionException {
        String func;
        String[] function = s.split("\\(");
        if(function.length != 2) throw new InvalidFunctionException("Invalid function form");
        String functionName = function[0].toLowerCase();
        String[] args = obtainArguments(function[1]);

        switch(functionName){
            case "make-ring":           if (args.length != 2) throw new InvalidFunctionException("make-ring requires 2 argument, given " + args.length);
                                        func = "make_ring(" + (this.variableName != null ? this.variableName + "," : "") + s.substring(s.indexOf("(") + 1, s.indexOf(")")) + ");";
                                        break;
            // Making a linear molecular chain structure
            case "make-chain":          if (args.length != 2) throw new InvalidFunctionException("make-chain requires 2 argument, given " + args.length);
                                        func = "make_chain(" + (this.variableName != null ? this.variableName + "," : "") + s.substring(s.indexOf("(") + 1, s.indexOf(")")) + ");";
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
            case "mirror":              if (args.length != 1) throw new InvalidFunctionException("mirror requires 1 argument, given " + args.length);
                                        func = s;
                                        break;
            case "convert-to-smiles":   if (args.length != 1) throw new InvalidFunctionException("convert-to-smiles requires 1 argument, given " + args.length);
                                        func = "toSmiles(" + args[0] + ");";
                                        break;
            case "check-validity":      if (args.length != 1) throw new InvalidFunctionException("check-validity requires 1 argument, given " + args.length);
                                        func = "checkValidity(" + args[0] + ");";
                                        break;
            case "render":              if (args.length != 1) throw new InvalidFunctionException("render requires 1 argument, given " + args.length);
                                        func = s;
                                        break;
            // Making a single-element molecular structure
            case "make":
                                        if (args.length != 1) throw new InvalidFunctionException("make requires 1 argument, given " + args.length);
                                        func = "make(" + (this.variableName != null ? this.variableName + "," : "") + s.substring(s.indexOf("(") + 1, s.indexOf(")")) + ");";
                                        break;
            //error because what is being use isn't in our language
            default:                    func = "ERROR"; //TODO: should throw exception? or ignore "ERROR"
                                        break;
        }
        return func;
    }

    static String[] obtainArguments(String function) throws InvalidFunctionException {
        String[] args  = function.split("\\)");
        if (args.length != 1 && args[0].equals(function)) throw new InvalidFunctionException("Invalid function form");
        args = Arrays.stream(args[0].split(",")).map(InputParser::sanitize).toArray(String[]::new); //trims whitespace
        return args;
    }


    //get rids of spaces in a string
    static String sanitize(String s){
        return s.replaceAll("\\s+","");
    }

    static String stripOutStructure(String s) {
        return s.replace("Structure", "");
    }

    /*
    public Structure getVariable(String vName){
        String name = sanitize(vName);
        for(Structure s: elements){
            if(s.getvName().equals(name)){
                return s;
            }
        }
        //to do throw undefined variable error
        return null;
    }
    */

    //for testing purposes only
    /*
    ArrayList<Structure> getAll(){
        return this.elements;
    }
    */
}

