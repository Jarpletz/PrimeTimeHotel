package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends MasterDAO<Reservation> {
    ReservationDAO() {
        super("reservations", new String[] {"user_id", "room_id", "start_date", "end_date", "status"});
    }

    public Reservation fetch(int id) {
        try {
            ResultSet rs = super.fetchResultSet(id);
            if (rs != null)
                return new Reservation(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchAll(){
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
            statement.setInt(2, Reservation.Status.CHECKED_IN.getCode());
            statement.setInt(3, Reservation.Status.SCHEDULED.getCode());
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchByUserList(List<Integer> userIds) {
        String placeholders = String.join(",", java.util.Collections.nCopies(userIds.size(), "?"));
        String sql = "SELECT * FROM Reservations WHERE status IN (0, 2) AND user_id IN (" + placeholders + ")";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
            statement.setInt(3, Reservation.Status.CHECKED_IN.getCode());
            statement.setInt(4, Reservation.Status.SCHEDULED.getCode());
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
            statement.setInt(4, Reservation.Status.CHECKED_IN.getCode());
            statement.setInt(5, Reservation.Status.SCHEDULED.getCode());
            statement.setInt(6, r.getId());
            return fetchReservations(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
