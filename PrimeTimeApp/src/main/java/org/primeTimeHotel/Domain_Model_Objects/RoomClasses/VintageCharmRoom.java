package org.primeTimeHotel.Domain_Model_Objects.RoomClasses;

import java.util.ArrayList;
import java.util.Objects;

public class VintageCharmRoom extends RoomAbstractClass {
    public VintageCharmRoom(){
        super();
    }

    public VintageCharmRoom(int floor, int roomNumber, double currentRate,
                            ArrayList<Bed> beds, boolean smokerStatus,
                            QualityLevel qualityLevel, RoomType type){
        super(floor, roomNumber, currentRate, beds,  smokerStatus, qualityLevel, type);
    }
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof VintageCharmRoom that)) return false;
        if (!super.equals(o)) return false;
        return that.getType() == getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getType());
    }
}
