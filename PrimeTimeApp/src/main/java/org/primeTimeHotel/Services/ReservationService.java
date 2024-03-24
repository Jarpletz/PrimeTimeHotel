package org.primeTimeHotel.Services;

import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ReservationService {

    ReservationDAO reservationDAO;

    public ReservationService(){
        reservationDAO = new ReservationDAO();
    }

    //returns false if the reservation conflicts with an already existing reservation,
    //true if there are no room conflicts
    public  boolean checkReservationValid(Reservation reservation){
        List<Reservation> conflictingRooms = reservationDAO.fetchConflictingReservations(reservation);
        return conflictingRooms.isEmpty();
    }

    //saves a new reservation in the system. Returns true if successful
    public boolean saveNewReservation(Reservation r){
        if(this.checkReservationValid(r)){
            return reservationDAO.insertReservation(r);
        }
        return false;
    }

    //update an existing reservation in the database. Returns true if successful
    public  boolean updateReservation(Reservation r){
        if(r.getId()<1) return false;

        if(this.checkReservationValid(r)){
            return reservationDAO.updateReservation(r);
        }
        return false;
    }

    public  Reservation createReservation(Date startDate, Date endDate, int roomId, int userId){
        return new Reservation(userId,roomId,startDate,endDate);
    }

    public List<Reservation> searchReservations(String guestInfo){
        List<Integer> userIds = new ArrayList<>();
        //TODO: get a list of all user IDs here whose accountInfo matches what was searched
        return reservationDAO.fetchByUserList(userIds);
    }
}
