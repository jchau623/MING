package ming.cpsc311.ubc;

import java.util.Arrays;
import java.util.ArrayList;

class Bond{
  private Structure structure;
  private int bondType;

  Bond(Structure structure, int bondType){
    this.structure = structure;
    this.bondType = bondType;
  }

  public Structure getStructure(){
    return this.structure;
  }

  public int getBondType(){
    return this.bondType;
  }
}