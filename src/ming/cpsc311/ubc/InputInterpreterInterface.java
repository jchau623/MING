package ming.cpsc311.ubc;

import Exceptions.InvalidFunctionException;

import java.util.ArrayList;
import java.util.HashMap;

public interface InputInterpreterInterface {
    /**
     * Entry-point for the interpreter
     * @param commands List of desugared MING instructions
     */
    void interpret(ArrayList<String> commands) throws NoSuchMethodException, InvalidFunctionException;
}
