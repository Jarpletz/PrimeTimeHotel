package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Reservation {
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
    private int id;
    private int userId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    private Status status;

    public Reservation(){
        id = -1;
        userId = -1;
        roomId = -1;
        startDate = null;
        endDate = null;
        status = Status.SCHEDULED;
    }

    public Reservation(int userId, int roomId,Date startDate,Date endDate){
        setUserId(userId);
        setRoomId(roomId);
        setStartDate(startDate);
        setEndDate(endDate);
        status = Status.SCHEDULED;
    }
    public Reservation(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getInt("user_id"),
                resultSet.getInt("room_id"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date")
        );
        this.id = resultSet.getInt("id");
        this.status = Status.fromCode(resultSet.getInt("status"));
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
    public Status getStatus(){return status;}

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
