import Exceptions.InvalidFunctionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class InputInterpreter implements InputInterpreterInterface{
    HashMap<String, Structure> structures = new HashMap<>();

    @Override
    public void interpret(ArrayList<String> commands) throws NoSuchMethodException, InvalidFunctionException {
        Method currentMethod = null;
        String vName;
        String function;
        for(String s : commands) {
            if (s.contains("=")) {
                String[] arr = s.split("=");
                vName = InputParser.stripOutStructure(InputParser.sanitize(arr[0]));
                function = InputParser.sanitize(arr[1]);
            } else {
                vName = null;
                function = InputParser.sanitize(s);
            }
            String[] f = function.split("\\(");
            String functionName = f[0].toLowerCase();
            String[] arguments = InputParser.obtainArguments(f[1]);
            Method method = obtainMethod(functionName);
            try {
                method.invoke(this, (Object[]) arguments);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a Structure from one element
     *
     * @param vName String of variable element is assigned to
     * @param eName String of element to be created
     * @return Structure
     */
    public Structure make(String vName, String eName) {

        return null;
    }

    /**
     * Creates a structure of a carbon ring
     * @param vName String of variable ring is assigned to
     * @param n_string Number of carbons in the ring
     * @param b_string TODO
     * @return Structure
     */
    public Structure make_ring(String vName, String n_string, String b_string) {
        int n = Integer.parseInt(n_string);
        Boolean b = Boolean.valueOf(b_string);
        return null;
    }

    /**
     * Creates a chain of elements
     * @param vName String of variable chain is assigned to
     * @param s Element used to create the chain
     * @return Structure
     */
    public Structure make_chain(String vName, String s) {
        return null;
    }

    /**
     * Returns a Structure from joining two Structures together
     *
     * @param s1        The first structure
     * @param s2        The second structure
     * @param bond_type_string Denotes single/double/triple/in/out bond
     * @return Structure
     */
    //TODO: Look into hashmap for the corresponding Structure!
    public Structure join(String s1, String s2, String bond_type_string) {
        int bond_type = Integer.parseInt(bond_type_string);
        return null;
    }

    /**
     * Produces the enantiomer of the given structure
     *
     * @param s The structure to be mirrored
     * @return Structure
     */
    //TODO: Look into hashmap for the corresponding Structure!
    public Structure mirror(String s) {
        return null;
    }

    /**
     * Converts MING Structure to SMILES notation
     *
     * @param s Structure to be converted to SMILES
     * @return String
     */
    //TODO: Look into hashmap for the corresponding Structure!
    public String toSmiles(String s) {
        return null;
    }

    /**
     * Checks validity of the given MING Structure
     *
     * @param s Structure to be checked
     * @return boolean
     */
    //TODO: Look into hashmap for the corresponding Structure!
    public boolean checkValidity(String s) {
        return false;
    }

    /**
     * Renders the structure given
     *
     * @param s Structure to be rendered
     */
    //TODO: Look into hashmap for the corresponding Structure!
    public void render(String s) {

    }

    private Method obtainMethod(String function) throws NoSuchMethodException {
        Method currentMethod;
        try {
            switch (function) {
                case "make":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class, String.class);
                    break;
                case "make_ring":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class, String.class, String.class);
                    break;
                case "make_chain":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class, String.class);
                    break;
                case "join":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class, String.class, String.class);
                    break;
                case "mirror":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class);
                    break;
                case "toSmiles":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class);
                    break;
                case "checkValidity":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class);
                    break;
                case "render":
                    currentMethod = InputInterpreter.class.getMethod(function, String.class);
                    break;
                default:
                    throw new NoSuchMethodException(function + " does not exist");
            }
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodException(function + " does not exist");
        }
        return currentMethod;
    }
}
