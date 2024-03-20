package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class UrbanEleganceRoom extends RoomAbstractClass{
    private enum RoomType{
        SUITE, DELUXE
    }

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
        if (!(o instanceof UrbanEleganceRoom that)) return false;
        if (!super.equals(o)) return false;
        return getRoomType() == that.getRoomType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoomType());
    }
}