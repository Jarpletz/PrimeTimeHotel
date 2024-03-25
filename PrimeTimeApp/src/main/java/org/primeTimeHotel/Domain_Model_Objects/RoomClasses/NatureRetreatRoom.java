package org.primeTimeHotel.Domain_Model_Objects.RoomClasses;

import java.util.ArrayList;
import java.util.Objects;

public class NatureRetreatRoom extends RoomAbstractClass {
    public NatureRetreatRoom(){
        super();
    }
    public NatureRetreatRoom(int floor, int roomNumber, double currentRate,
                             ArrayList<Bed> beds, boolean smokerStatus,
                             QualityLevel qualityLevel, RoomType type){
        super(floor, roomNumber, currentRate, beds,  smokerStatus, qualityLevel, type);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof NatureRetreatRoom that)) return false;
        if (!super.equals(o)) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType());
    }
}
