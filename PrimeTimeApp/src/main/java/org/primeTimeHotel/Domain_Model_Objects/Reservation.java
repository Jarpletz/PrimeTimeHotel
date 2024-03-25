package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.Date;
import java.util.Objects;

public class Reservation extends AbstractDomainModelObject {
    public enum Status {
        CHECKED_IN(0),
        CHECKED_OUT(1),
        SCHEDULED(2),
        CANCELED(3);

        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Reservation.Status fromCode(int code) {
            for (Reservation.Status type: Reservation.Status.values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid code: " + code);
        }
    }
    private int userId;
    private int roomId;
    private Date startDate;
    private Date endDate;
    private Status status;

    //public Reservation(){
    //    this (-1, -1, null, null);
    //}
    public Reservation(int userId, int roomId,Date startDate,Date endDate){
        this.id = -1;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.SCHEDULED;
    }

    public int getUserId() {
        return userId;
    }
    public int getRoomId() {
        return roomId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Status getStatus(){return status;}

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return id == that.id && userId == that.userId && roomId == that.roomId && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roomId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Reservation{\n" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                "\n}";
    }
}
