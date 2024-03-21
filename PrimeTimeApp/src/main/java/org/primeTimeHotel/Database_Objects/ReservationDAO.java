package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Reservation;
import org.primeTimeHotel.Domain_Model_Objects.ReservationStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReservationDAO {
    private List<Reservation> tempData;

    public ReservationDAO(){
        tempData = new ArrayList<Reservation>();

        File myFile = new File("src/main/resources/org/primeTimeHotel/reservationsTemp.csv");
        try{
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] data = line.split(",");
                tempData.add(new Reservation(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        new Date(Long.parseLong(data[3])),
                        new Date(Long.parseLong(data[4])),
                        ReservationStatus.values()[Integer.parseInt(data[5])]
                ));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Reservation CSV FIle Not Found!");
        }


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
                        (r.getStartDate().compareTo(startDate)<0 && r.getEndDate().compareTo(endDate)>0))
                .toList();
    }

    public List<Reservation> fetchConflictingReservations(Reservation r){
         return tempData.stream().filter(reservation->
                 (reservation.getStartDate().compareTo(r.getEndDate())<0 && reservation.getEndDate().compareTo(r.getStartDate())>0)
                 && reservation.getRoomId() == r.getRoomId()
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
