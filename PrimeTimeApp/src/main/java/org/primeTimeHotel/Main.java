package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RootDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

public class Main {
    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();

        Reservation r;
        for (int i = 1; (r = dao.fetch(i)) != null; ++i) {
            System.out.println(r);
        }

        RootDAO.close();
    }
}