package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Reservation;
import org.primeTimeHotel.Domain_Model_Objects.ReservationStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationDAO extends  MasterDAO {
    private List<Reservation> tempData;


    public ReservationDAO(){
        super();
    }

    public Reservation fetchReservation(int reservationId){
        if(connection == null) return null;
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM reservations WHERE id = ?");
            statement.setInt(1,reservationId);
            ResultSet  rs= statement.executeQuery();
            List<Reservation> reservations = resultSetToReservationList(rs);
            if(reservations.isEmpty()) return null;
            return  reservations.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> fetchAllReservations(){
        List<Reservation> reservations = new ArrayList<>();
        if (connection != null) {
            try {
                PreparedStatement statement =connection.prepareStatement("SELECT * FROM reservations");
                ResultSet  rs= statement.executeQuery();
                reservations = resultSetToReservationList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return reservations;
    }

    public List<Reservation> fetchByUserAll(int userId){
        List<Reservation> reservations = new ArrayList<>();
        if (connection != null) {
            try {
                PreparedStatement statement =connection.prepareStatement("SELECT * FROM reservations WHERE user_id = ?");
                statement.setInt(1,userId);
                ResultSet  rs= statement.executeQuery();
                reservations = resultSetToReservationList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return reservations;
    }
    public List<Reservation> fetchByUserCurrent(int userId){
        return tempData.stream()
                .filter(r->r.getUserId() == userId
                        && r.getStatus()!=ReservationStatus.CANCELED
                        && r.getStatus()!=ReservationStatus.CHECKED_OUT)
                .toList();
    }

    public List<Reservation> fetchByUserList(List<Integer> userIds){
        return tempData.stream()
                .filter(r->
                                userIds.contains(r.getUserId())
                                && r.getStatus()!=ReservationStatus.CANCELED
                                && r.getStatus()!=ReservationStatus.CHECKED_OUT)
                .toList();
    }

    public List<Reservation> fetchByOverlappingDates(Date startDate, Date endDate){
        return tempData.stream()
                .filter(r->
                        (r.getStartDate().compareTo(startDate)<0 && r.getEndDate().compareTo(endDate)>0)
                        && r.getStatus()!=ReservationStatus.CANCELED
                        && r.getStatus()!=ReservationStatus.CHECKED_OUT)
                .toList();
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
       // if(updateReservation(r)) return true;
        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Reservations(user_id,room_id,start_date,end_date,status)" +  " VALUES (?, ?, ?,? ,?)");
            stmt.setInt(1,r.getUserId());
            stmt.setInt(2, r.getRoomId());
            stmt.setDate(3,r.getStartDate());
            stmt.setDate(4,r.getEndDate());
            stmt.setInt(5, r.getStatus().ordinal());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
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

    private List<Reservation> resultSetToReservationList(ResultSet rs) throws SQLException {
        List<Reservation> reservations= new ArrayList<>();
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUserId(rs.getInt("user_id"));
            reservation.setRoomId(rs.getInt("room_id"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setStatus(ReservationStatus.values()[rs.getInt("status")]);
            reservations.add(reservation);
        }
        return reservations;
    }

}
