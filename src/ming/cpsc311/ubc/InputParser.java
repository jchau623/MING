import java.util.Scanner;
import java.util.Arrays;

class InputParser {
    public String parse(String s) {
        String func = "";
        if(s.indexOf("=") > -1){
            //this is assignment
        } else {
            //getting function name 
            String functionName = s.split("\\(")[0];
            switch(functionName){
                case "make-ring":           func = s;
                                            break;
                case "make-chain":          func = s;
                                            break;
                case "bond":                String args[] = s.substring(s.indexOf("(")+1, s.indexOf(")")+1).split(",");
                                            String lastArg = args[args.length-1];
                                            args[args.length-1] = lastArg.substring(0, lastArg.length() -1);
                                            switch(args[args.length-1].replaceAll("\\s+","")){
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
                                            func = func + args[0] + ", " + args[1] + ", " + args[2] + ", " + args[3] + ");" ;
                                            break;
                case "mirror":              func = s;
                                            break;
                case "conver-to-smiles":    func = s;
                                            break;
                case "check-validity":      func = "check";
                                            break;
                case "render":              func = s;
                                            break;
                //error because what is being use isn't in our language
                default:                    func = "ERROR";
                                            break;
            }
        }
        return func;
    }
}

