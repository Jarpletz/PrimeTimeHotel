package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Reservation;
import org.primeTimeHotel.Domain_Model_Objects.ReservationStatus;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends  MasterDAO {
    private List<Reservation> tempData;

    public Reservation fetchReservation(int reservationId){
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return new Reservation(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchAllReservations(){
        String sql = "SELECT * FROM reservations";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchByUserAll(int userId){
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,userId);
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Reservation> fetchByUserCurrent(int userId){
        String sql = "SELECT * FROM Reservations WHERE user_id = ? AND status IN (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(3, ReservationStatus.SCHEDULED.getCode());
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchByUserList(List<Integer> userIds) {
        String placeholders = String.join(",", java.util.Collections.nCopies(userIds.size(), "?"));
        String sql = "SELECT * FROM Reservations WHERE status IN (?, ?) AND user_id IN (" + placeholders + ")";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(2, ReservationStatus.SCHEDULED.getCode());
            for (int i = 0; i < userIds.size(); i++)
                statement.setInt(i + 1, userIds.get(i));
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchByOverlappingDates(Date startDate, Date endDate){
        String sql = "SELECT * FROM Reservations WHERE NOT (start_date > ? OR end_date < ?) AND status IN (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(endDate.getTime()));
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setInt(3, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(4, ReservationStatus.SCHEDULED.getCode());
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchConflictingReservations(Reservation r){
        String sql = "SELECT * FROM Reservations WHERE (start_date < ? AND end_date > ?) " +
                     "AND room_id = ? AND status IN (?, ?) AND id != ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, r.getEndDate());
            statement.setDate(2, r.getStartDate());
            statement.setInt(3, r.getRoomId());
            statement.setInt(4, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(5, ReservationStatus.SCHEDULED.getCode());
            statement.setInt(6, r.getId());
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertReservation(Reservation r){
        if (fetchReservation(r.getId()) != null) {
            String sql = "INSERT INTO Reservations(user_id, room_id, start_date, end_date, status) " +
                         "VALUES (?, ?, ?, ? ,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,r.getUserId());
                statement.setInt(2, r.getRoomId());
                statement.setDate(3,r.getStartDate());
                statement.setDate(4,r.getEndDate());
                statement.setInt(5, r.getStatus().ordinal());
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateReservation(Reservation r){
        if (fetchReservation(r.getId()) != null) {
            String sql = "UPDATE Reservations " +
                         "SET user_id = ?, room_id = ?, start_date = ?, end_date = ?, status = ? " +
                         "WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, r.getUserId());
                statement.setInt(2, r.getRoomId());
                statement.setDate(3, new java.sql.Date(r.getStartDate().getTime()));
                statement.setDate(4, new java.sql.Date(r.getEndDate().getTime()));
                statement.setInt(5, r.getStatus().getCode());
                statement.setInt(6, r.getId());
                return statement.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean deleteReservation(int reservationId){
        if (fetchReservation(reservationId) != null) {
            String sql = "DELETE FROM Reservations WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, reservationId);
                return statement.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteReservation(Reservation r){
        return deleteReservation(r.getId());
    }

    private List<Reservation> fetchReservations(PreparedStatement statement) {
        if (connection != null) {
            try {
                return resultSetToReservationList(statement.executeQuery());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<Reservation> resultSetToReservationList(ResultSet rs) throws SQLException {
        List<Reservation> reservations= new ArrayList<>();
        while (rs.next()) {
            reservations.add(new Reservation(rs));
        }
        return reservations;
    }
}
