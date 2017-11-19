import java.util.Arrays;
import java.util.ArrayList;


class Structure {

    private String eName; //element name
    private String vName; //variable name
    private ArrayList<Bond> bonds;

    Structure(String vname, String ename){
        this.vName = vname;
        this.eName = ename;
        this.bonds = new ArrayList<>();
    }

    public ArrayList<Bond> getBonds(){
        return this.bonds;
    }

    public void addBond(Structure el, int bondType){
        bonds.add(new Bond(el, bondType));
    }

    public String getvName(){
        return this.vName;
    }

    public String geteName(){
        return this.eName;
    }

}
