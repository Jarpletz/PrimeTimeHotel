package org.primeTimeHotel.Domain_Model_Objects.RoomClasses;

import java.util.ArrayList;
import java.util.Objects;

public class RoomAbstractClass {
    public enum RoomType{
        SINGLE, DOUBLE, FAMILY, SUITE,STANDARD, DELUXE
    }

    public enum QualityLevel {
        EXECUTIVE, BUSINESS, COMFORT, ECONOMY
    }
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

    private static int roomID = 0;
    private int roomNumber;
    private QualityLevel qualityLevel;

    //To be changed by logan --CHANGED!
    private int floor;
    private double currentRate;
    private boolean smokerStatus;
    private RoomType type;

    //Also to be changed by logan --CHANGED!
    private ArrayList<Bed> beds;

    ///Accessors and Mutators
    public void setId(int id) {
        this.roomID = id;
    }

    public int getId() {
        return roomID;
    }

    public void setQualityLevel(QualityLevel qualityLevel) {
        this.qualityLevel = qualityLevel;
    }

    public QualityLevel getQualityLevel() {
        return qualityLevel;
    }

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

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
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
