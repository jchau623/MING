public interface InputInterpreterInterface {
    /**
     * Creates a Structure from one element
     * @param s String of element to be created
     * @return Structure
     */
    Structure make(String s);

    /**
     * Creates a structure of a carbon ring
     * @param n Number of carbons in the ring
     * @param b TODO
     * @return Structure
     */
    Structure make_ring(int n, boolean b);

    /**
     * Creates a chain of elements
     * @param s Element used to create the chain
     * @return Structure
     */
    Structure make_chain(String s);

    /**
     * Returns a Structure from joining two Structures together
     * @param s1 The first structure
     * @param s2 The second structure
     * @param index_1 The index of the element in s1 that will be joined to s2
     * @param index_2 The index of the element in s2 that will be joined to s1
     * @param bond_type Denotes single/double/triple/in/out bond
     * @return Structure
     */
    Structure join(Structure s1, Structure s2, int index_1, int index_2, int bond_type);

    /**
     * Produces the enantiomer of the given structure
     * @param  s The structure to be mirrored
     * @return Structure
     */
    Structure mirror(Structure s);

    /**
     * Converts MING Structure to SMILES notation
     * @param s Structure to be converted to SMILES
     * @return String
     */
    String toSmiles(Structure s);

    /**
     * Checks validity of the given MING Structure
     * @param s Structure to be checked
     * @return boolean
     */
    boolean checkValidity(Structure s);

    /**
     * Renders the structure given
     * @param s Structure to be rendered
     */
    void render(Structure s);
}
