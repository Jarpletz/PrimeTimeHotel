package org.primeTimeHotel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReservationDAO {
    private List<Reservation> tempData;

    ReservationDAO(){
        tempData = Arrays.asList(
                new Reservation(1,69,34,new Date(2024,3,18),new Date(2024,3,19),ReservationStatus.SCHEDULED),
                new Reservation(2,65,34,new Date(2024,3,16),new Date(2024,3,20),ReservationStatus.CANCELED),
                new Reservation(3,23,34,new Date(2024,3,19),new Date(2024,3,22),ReservationStatus.CHECKED_IN),
                new Reservation(4,23,33,new Date(2023,11,27),new Date(2024,11,30),ReservationStatus.CHECKED_OUT),
                new Reservation(5,43,31,new Date(2022,12,27),new Date(2023,1,5),ReservationStatus.CANCELED),
                new Reservation(6,24,26,new Date(1987,7,6),new Date(1987,7,12),ReservationStatus.CHECKED_OUT)
                );
    }

    public Reservation fetchReservation(int reservationId){
        for(Reservation r : tempData) {
            if(r.getId() == reservationId) return r;
        }
        return null;
    }

    public List<Reservation> fetchAllReservations(){
        return tempData;
    }

    public List<Reservation> fetchByUserAll(int userId){
        return tempData.stream().filter(r->r.getUserId() == userId).toList();
    }
    public List<Reservation> fetchByUserCurrent(int userId){
        return tempData.stream()
                .filter(r->r.getUserId() == userId
                        && r.getStatus()!=ReservationStatus.CANCELED
                        && r.getStatus()!=ReservationStatus.CHECKED_OUT)
                .toList();
    }

    public List<Reservation> fetchByOverlappingDates(Date startDate, Date endDate){
        return tempData.stream()
                .filter(r->
                        r.getStartDate().compareTo(endDate)<0 || r.getEndDate().compareTo(startDate)>0)
                .toList();
    }

    public List<Reservation> fetchConflictingReservations(Reservation r){
         return tempData.stream().filter(reservation->
                 (reservation.getStartDate().compareTo(r.getEndDate())<0 || reservation.getEndDate().compareTo(r.getStartDate())>0)
                 && r.getRoomId() == r.getRoomId() )
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
