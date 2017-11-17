import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class InputParser {
    //keep a list of all instantiated varialbes
    //error checking later in parseFunction will use this to see if a variable is instantiated statically
    public ArrayList<Structure> elements = new ArrayList<Structure>();

    public String parse(String s) {
        String func = "";
        if(s.indexOf("=") > -1){
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

    public String parseFunction(String s){
        String func = "";
        String functionName = s.split("\\(")[0];
            switch(functionName){
                case "make-ring":           func = s;
                                            break;
                case "make-chain":          func = s;
                                            break;
                case "bond":                String args[] = s.substring(s.indexOf("(")+1, s.indexOf(")")+1).split(",");
                                            String lastArg = args[args.length-1];
                                            args[args.length-1] = lastArg.substring(0, lastArg.length() -1);
                                            Structure firstE = getVariable(args[0]);
                                            Structure secondE = getVariable(args[1]);
                                            int bondType = Integer.parseInt(sanitize(args[2]));
                                            firstE.addBond(secondE, bondType);
                                            secondE.addBond(firstE, bondType);
                                            switch(sanitize(args[args.length-1])){
                                                case "1":   func = "single-bond(";
                                                            break;
                                                case "2":   func = "double-bond(";
                                                            break;
                                                case "3":   func = "triple-bond(";
                                                            break;
                                                case "4":   func = "in-bond(";
                                                            break;
                                                case "5":   func = "out-bond(";
                                                            break;
                                            }
                                            func = func + args[0] + "," + args[1] + "," + args[2] + ");" ;
                                            break;
                case "mirror":              func = s;
                                            break;
                case "conver-to-smiles":    func = s;
                                            break;
                case "check-validity":      func = s;
                                            break;
                case "render":              func = s;
                                            break;
                case "make":                func = "make(" + s.substring(s.indexOf("(") + 1, s.indexOf(")")) + ")";
                                            break;
                //error because what is being use isn't in our language
                default:                    func = "ERROR";
                                            break;
            }
        return func;
    }


    //get rids of spaces in a string
    public String sanitize(String s){
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
    public ArrayList<Structure> getAll(){
        return this.elements;
    }
}

