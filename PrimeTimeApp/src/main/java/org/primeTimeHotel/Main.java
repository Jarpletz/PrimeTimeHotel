package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.PaymentInfoDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RootDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

public class Main {
    public static void main(String[] args) {
        PaymentInfoDAO dao = new PaymentInfoDAO();


        RootDAO.close();
    }
}