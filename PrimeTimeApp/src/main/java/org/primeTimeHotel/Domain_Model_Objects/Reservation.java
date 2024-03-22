package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private int id;
    private int userId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    private ReservationStatus status;

    public Reservation(){
        id = -1;
        userId = -1;
        roomId = -1;
        startDate = null;
        endDate = null;
        status = ReservationStatus.SCHEDULED;
    }

    public Reservation(int userId, int roomId,Date startDate,Date endDate){
        setUserId(userId);
        setRoomId(roomId);
        setStartDate(startDate);
        setEndDate(endDate);
        status = ReservationStatus.SCHEDULED;
    }

    public  Reservation(int id, int userId, int roomId,Date startDate,Date endDate,ReservationStatus status){
        this(userId,roomId,startDate,endDate);
        setId(id);
        setStatus(status);
    }

    public int getId() {
        return id;
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
    public ReservationStatus getStatus(){return status;}

    public void setId(int id) {
        this.id = id;
    }
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

    public void setStatus(ReservationStatus status) {
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
