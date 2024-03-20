package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public abstract class RoomAbstractClass {
    public enum QualityLevel {
        EXECUTIVE, BUSINESS, COMFORT, ECONOMY
    }

    private int roomNumber;
    private QualityLevel qualityLevel;

    //To be changed by logan
    private int floor;
    private double currentRate;
    private boolean smokerStatus;

    //Also to be change by logan
    private String beds;

    ///Accessors and Mutators
    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
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
