package org.primeTimeHotel.Domain_Model_Objects;

import java.util.ArrayList;
import java.util.Objects;

public class RoomAbstractClass {

    protected enum RoomType{
        SINGLE, DOUBLE, FAMILY, SUITE,STANDARD, DELUXE
    }

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

    private static int roomID = 0;
    private int roomNumber;
    private QualityLevel qualityLevel;

    private int floor;
    private double currentRate;
    private boolean smokerStatus;


    private ArrayList<Bed> beds;

    public RoomAbstractClass(int floor, int roomNumber, double currentRate, ArrayList<Bed> beds, boolean smokerStatus, QualityLevel qualityLevel){
        roomID++;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.currentRate = currentRate;
        this.beds = beds;
        this.qualityLevel = qualityLevel;
        this.smokerStatus = smokerStatus;
    }

    public RoomAbstractClass(){
        roomID++;
        beds = new ArrayList<>();
        smokerStatus = false;
    }

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
