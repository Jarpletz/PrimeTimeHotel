package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.MasterDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();
        System.out.println("ALL RESERVATIONs: ______________________________________________");
        dao.fetchAllReservations().forEach(System.out::println);

        System.out.println("RESERVATION 2: ______________________________________________");
        System.out.println(dao.fetchReservation(2));

        MasterDAO.close();
    }
}