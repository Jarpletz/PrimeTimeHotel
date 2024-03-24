package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.*;
import java.util.Objects;

public class Reservation {
    private int id;
    private int userId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    private ReservationStatus status;

    public Reservation(){
        this(-1, -1, null, null);
    }


    public Reservation(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getInt("user_id"),
                resultSet.getInt("room_id"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date")
        );
        this.id = resultSet.getInt("id");
        this.status = ReservationStatus.fromCode(resultSet.getInt("status"));
    }

    public Reservation(int userId, int roomId, Date startDate, Date endDate){
        this.id = -1;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.SCHEDULED;
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
