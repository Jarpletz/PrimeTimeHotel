package org.primeTimeHotel.Domain_Model_Objects;

import java.util.ArrayList;
import java.util.Objects;

public class Floors {
    private ArrayList<NatureRetreatRoom> natureFloor;
    private ArrayList<UrbanEleganceRoom> urbanFloor;
    private ArrayList<VintageCharmRoom> vintageFloor;

    public Floors(ArrayList<NatureRetreatRoom> natureFloor, ArrayList<UrbanEleganceRoom> urbanFloor,
                  ArrayList<VintageCharmRoom> vintageFloor) {
        this.natureFloor = natureFloor;
        this.urbanFloor = urbanFloor;
        this.vintageFloor = vintageFloor;
    }

    public void addToNatureFloor(NatureRetreatRoom newRoom){
        natureFloor.add(newRoom);
    }
    public void addToEleganceFloor(UrbanEleganceRoom newRoom){
        urbanFloor.add(newRoom);
    }
    public void addToVintageFloor(VintageCharmRoom newRoom){
        vintageFloor.add(newRoom);
    }

    public ArrayList<NatureRetreatRoom> getNatureFloor(){
        return natureFloor;
    }

    public ArrayList<UrbanEleganceRoom> getUrbanFloor() {
        return urbanFloor;
    }

    public ArrayList<VintageCharmRoom> getVintageFloor() {
        return vintageFloor;
    }

    //More specific accessors to come
}
