package org.primeTimeHotel.Services;

import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.GuestAccount;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.RoomAbstractClass;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ReservationService {

    ReservationDAO reservationDAO;
    RoomDAO roomDAO;

    public ReservationService(){
        reservationDAO = new ReservationDAO();
        roomDAO = new RoomDAO();
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

    public List<RoomAbstractClass> getAvalibleRooms(Date startDate, Date endDate, int floor){
        //Fetch conflicting rooms first
        List<Reservation> conflictingRooms = reservationDAO.fetchByOverlappingDates(startDate,endDate);
        List<Integer> conflictingRoomIDs = conflictingRooms.stream().map(Reservation::getRoomId).toList();

        //get rooms that dont have those room ID's and match floor number
        List<RoomAbstractClass> availableRooms = roomDAO.getAvailable(conflictingRoomIDs, floor);
        if(availableRooms == null){
            availableRooms = new ArrayList<>();
        }
        return availableRooms;

    }

    public Reservation selectRoom(GuestAccount account, RoomAbstractClass roomToBeSelected, Date startDate, Date endDate){
        Reservation newReservation =  createReservation(startDate,endDate, roomToBeSelected.getId(),account.getId());
        reservationDAO.insertReservation(newReservation);
        return newReservation;
    }

}
