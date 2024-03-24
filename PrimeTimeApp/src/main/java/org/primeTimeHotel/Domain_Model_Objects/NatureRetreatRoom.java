package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class NatureRetreatRoom extends RoomAbstractClass {


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
