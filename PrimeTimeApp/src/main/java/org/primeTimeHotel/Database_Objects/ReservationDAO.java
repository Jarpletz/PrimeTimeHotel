package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Reservation;
import org.primeTimeHotel.Domain_Model_Objects.ReservationStatus;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ReservationDAO extends RootDAO {
    private List<Reservation> tempData = new ArrayList<>();

    public Reservation fetchReservation(int reservationId){
        String sql = "SELECT * FROM Reservations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> fetchAllReservations(){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> fetchByUserAll(int userId){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    public List<Reservation> fetchByUserCurrent(int userId){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations WHERE user_id = ? AND (status = ? OR status = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(3, ReservationStatus.SCHEDULED.getCode());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> fetchByUserList(List<Integer> userIds){
        List<Reservation> reservations = new ArrayList<>();

        String placeholders = String.join(",", java.util.Collections.nCopies(userIds.size(), "?"));
        String sql = "SELECT * FROM Reservations WHERE (status = 0 OR status = 2) AND user_id IN (" + placeholders + ")";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(2, ReservationStatus.SCHEDULED.getCode());
            // Set each user ID in the prepared statement
            for (int i = 0; i < userIds.size(); i++) {
                statement.setInt(i + 1, userIds.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> fetchByOverlappingDates(Date startDate, Date endDate){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations WHERE NOT (start_date > ? OR end_date < ?) AND status IN (0, 2)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(endDate.getTime()));
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setInt(3, ReservationStatus.CHECKED_IN.getCode());
            statement.setInt(4, ReservationStatus.SCHEDULED.getCode());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> fetchConflictingReservations(Reservation r){
         return tempData.stream().filter(reservation->
                 (reservation.getStartDate().compareTo(r.getEndDate())<0 && reservation.getEndDate().compareTo(r.getStartDate())>0)
                 && reservation.getRoomId() == r.getRoomId()
                 && reservation.getStatus()!=ReservationStatus.CANCELED
                 && reservation.getStatus()!=ReservationStatus.CHECKED_OUT
                 && reservation.getId() != r.getId())
                .toList();
    }

    public boolean insertReservation(Reservation r){
        //try to update before inserting a new one
        if(updateReservation(r)) return true;
        if(r.getId()<0 ) r.setId(tempData.getLast().getId() + 1);
        tempData.add(r);
        return true;
    }

    public boolean updateReservation(Reservation r){
        if(r.getId()<0) return false;

        for(Reservation reservation : tempData){
            if(reservation.getId() == r.getId()){
                reservation = r;
                return true;
            }
        }
        return false;
    }
    public boolean deleteReservation(int reservationId){
       for(int i= 0;i<tempData.size();i++){
           if(tempData.get(i).getId() == reservationId){
               tempData.remove(i);
               return true;
           }
       }
       return  false;
    }

    public boolean deleteReservation(Reservation r){
        return tempData.remove(r);
    }



}
