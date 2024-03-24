package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class NatureRetreatRoom extends RoomAbstractClass {


    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof NatureRetreatRoom that)) return false;
        if (!super.equals(o)) return false;
        return getRoomType() == that.getRoomType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoomType());
    }
}
