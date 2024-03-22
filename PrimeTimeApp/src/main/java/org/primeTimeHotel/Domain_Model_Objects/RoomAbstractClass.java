package org.primeTimeHotel.Domain_Model_Objects;

import java.util.ArrayList;
import java.util.Objects;

public abstract class RoomAbstractClass {

    public static class Bed{
        public enum BedType{SINGLE, DOUBLE, QUEEN};
        private BedType type;
        Bed(){}
        public void setType(BedType type){this.type = type;}
        public BedType getType() {return type;}

    }
    public enum QualityLevel {
        EXECUTIVE, BUSINESS, COMFORT, ECONOMY
    }

    private int roomNumber;
    private QualityLevel qualityLevel;

    //To be changed by logan --CHANGED!
    private int floor;
    private double currentRate;
    private boolean smokerStatus;

    //Also to be changed by logan --CHANGED!
    private ArrayList<Bed> beds;

    ///Accessors and Mutators
    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public void setBeds(Bed newBeds) {
        beds.add(newBeds);
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public boolean isSmokerStatus() {
        return smokerStatus;
    }

    public void setSmokerStatus(boolean smokerStatus) {
        this.smokerStatus = smokerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomAbstractClass that)) return false;
        return getRoomNumber() == that.getRoomNumber() && getFloor() == that.getFloor() && Double.compare(getCurrentRate(), that.getCurrentRate()) == 0 && isSmokerStatus() == that.isSmokerStatus() && qualityLevel == that.qualityLevel && Objects.equals(getBeds(), that.getBeds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), qualityLevel, getFloor(), getCurrentRate(), isSmokerStatus(), getBeds());
    }



}
