package org.primeTimeHotel.Domain_Model_Objects;

import java.util.ArrayList;
import java.util.Objects;

public class UrbanEleganceRoom extends RoomAbstractClass{

    public UrbanEleganceRoom(){
        super();
    }

    public UrbanEleganceRoom(int floor, int roomNumber, double currentRate, ArrayList<Bed> beds, boolean smokerStatus, QualityLevel qualityLevel){
        super(floor, roomNumber, currentRate, beds, smokerStatus, qualityLevel);
    }
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof UrbanEleganceRoom that)) return false;
        if (!super.equals(o)) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType());
    }
}
