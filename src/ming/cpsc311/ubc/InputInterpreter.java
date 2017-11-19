public class InputInterpreter implements InputInterpreterInterface{

    /**
     * Creates a Structure from one element
     *
     * @param s String of element to be created
     * @return Structure
     */
    public Structure make(String s) {
        return null;
    }

    /**
     * Creates a structure of a carbon ring
     *
     * @param n Number of carbons in the ring
     * @param b TODO
     * @return Structure
     */
    public Structure make_ring(int n, boolean b) {
        return null;
    }

    /**
     * Creates a chain of elements
     *
     * @param s Element used to create the chain
     * @return Structure
     */
    public Structure make_chain(String s) {
        return null;
    }

    /**
     * Returns a Structure from joining two Structures together
     *
     * @param s1        The first structure
     * @param s2        The second structure
     * @param index_1   The index of the element in s1 that will be joined to s2
     * @param index_2   The index of the element in s2 that will be joined to s1
     * @param bond_type Denotes single/double/triple/in/out bond
     * @return Structure
     */
    public Structure join(Structure s1, Structure s2, int index_1, int index_2, int bond_type) {
        return null;
    }

    /**
     * Produces the enantiomer of the given structure
     *
     * @param s The structure to be mirrored
     * @return Structure
     */
    public Structure mirror(Structure s) {
        return null;
    }

    /**
     * Converts MING Structure to SMILES notation
     *
     * @param s Structure to be converted to SMILES
     * @return String
     */
    public String toSmiles(Structure s) {
        return null;
    }

    /**
     * Checks validity of the given MING Structure
     *
     * @param s Structure to be checked
     * @return boolean
     */
    public boolean checkValidity(Structure s) {
        return false;
    }

    /**
     * Renders the structure given
     *
     * @param s Structure to be rendered
     */
    public void render(Structure s) {

    }
}
