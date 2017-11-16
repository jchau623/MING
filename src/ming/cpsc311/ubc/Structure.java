import java.util.Arrays;
import java.util.ArrayList;


class Structure {

    public String eName;
    public String vName;
    public ArrayList<Bond> bonds;

    Structure(String vname, String ename){
        this.vName = vname;
        this.eName = ename;
        this.bonds = new ArrayList<Bond>();
    }

    public ArrayList<Bond> getBonds(){
        return this.bonds;
    }

    public void addBond(Structure el, int bondType){
        bonds.add(new Bond(el, bondType));
    }

    public String getVname(){
        return this.vName;
    }

    public String geteName(){
        return this.eName;
    }

}
