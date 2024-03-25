package org.primeTimeHotel.Domain_Model_Objects.RoomClasses;

public class Bed{
    public enum BedType{SINGLE, DOUBLE, QUEEN};
    private BedType type;
    public Bed(){}
    public Bed(BedType type){this.type = type;}
    public void setType(BedType type){this.type = type;}
    public BedType getType() {return type;}

}