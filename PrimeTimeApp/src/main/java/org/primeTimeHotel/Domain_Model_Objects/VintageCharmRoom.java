package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class VintageCharmRoom extends RoomAbstractClass {

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
        if (!(o instanceof VintageCharmRoom that)) return false;
        if (!super.equals(o)) return false;
        return that.getRoomType() == getRoomType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoomType());
    }
}
