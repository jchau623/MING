package ming.cpsc311.ubc;

import Exceptions.InvalidFunctionException;
import Exceptions.StructureNotFoundException;
import ming.cpsc311.ubc.InputInterpreterInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class InputInterpreter implements InputInterpreterInterface {
    private HashMap<String, Structure> structures = new HashMap<>();

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
    private Structure make(String vName, String eName) {
        Structure s = new Structure(vName, eName);
        this.structures.put(vName, s);
        return s;
    }

    /**
     * Creates a structure of a carbon ring
     * @param vName String of variable ring is assigned to
     * @param n_string Number of carbons in the ring
     * @param b_string TODO
     * @return Structure
     */
    //TODO: Problem - how do you bond a certain carbon in the ring to another structure without any indexes?
    private Structure make_ring(String vName, String n_string, String b_string) {
        int n = Integer.parseInt(n_string);
        Boolean b = Boolean.valueOf(b_string); // TODO: this boolean option is for showing/hiding the letter C over Carbons (false = hide)
        /*
            Make n Carbons and bond the Carbons together into a ring
         */
        for (int i = 0; i < n; i++) {

        }
        return null;
    }

    /**
     * Creates a chain of elements
     * @param vName String of variable chain is assigned to
     * @param n_string Number of elements in this chain
     * @param s Element used to create the chain
     * @return Structure
     */
    private Structure make_chain(String vName, String n_string, String s) {
        int n = Integer.parseInt(n_string);
        for (int i = 0; i < n; i++) {

        }
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
    private Structure join(String s1, String s2, String bond_type_string) throws StructureNotFoundException {
        int bond_type = Integer.parseInt(bond_type_string);
        Structure x = this.structures.get(s1);
        Structure y = this.structures.get(s2);
        if (x == null) throw new StructureNotFoundException("Structure " + s1 + " does not exist.");
        if (y == null) throw new StructureNotFoundException("Structure " + s2 + " does not exist.");
        x.addBond(y, bond_type);
        y.addBond(x, bond_type);
        return x;
    }

    /**
     * Produces the enantiomer of the given structure
     *
     * @param s The structure to be mirrored
     * @return Structure
     */
    //TODO: Look into hashmap for the corresponding Structure!
    private Structure mirror(String s) {
        return null;
    }

    /**
     * Converts MING Structure to SMILES notation
     *
     * @param s Structure to be converted to SMILES
     * @return String
     */
    //TODO: Look into hashmap for the corresponding Structure!
    private String toSmiles(String s) {
        return null;
    }

    /**
     * Checks validity of the given MING Structure
     *
     * @param s Structure to be checked
     * @return boolean
     */
    //TODO: Look into hashmap for the corresponding Structure!
    private boolean checkValidity(String s) {
        return false;
    }

    /**
     * Renders the structure given
     *
     * The angle of the line formed by the bond is (# of bonds)/360.
     *
     * @param s Structure to be rendered
     */
    //TODO: Look into hashmap for the corresponding Structure!
    private void render(String s) throws StructureNotFoundException {
        Structure x = this.structures.get(s);
        System.out.println("hello bitch");
        if (x == null) throw new StructureNotFoundException("Structure " + s + " does not exist.");

    }

    private Method obtainMethod(String function) throws NoSuchMethodException {
        Method currentMethod;
        try {
            switch (function) {
                case "bond":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class, String.class, String.class);
                    break;
                case "make":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class, String.class);
                    break;
                case "make_ring":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class, String.class, String.class);
                    break;
                case "make_chain":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class, String.class, String.class);
                    break;
                case "mirror":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class);
                    break;
                case "toSmiles":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class);
                    break;
                case "checkValidity":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class);
                    break;
                case "render":
                    currentMethod = InputInterpreter.class.getDeclaredMethod(function, String.class);
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
