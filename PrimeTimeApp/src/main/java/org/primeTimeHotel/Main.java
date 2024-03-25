package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.AccountDAO;
import org.primeTimeHotel.Database_Objects.MasterDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.*;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.Bed;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.NatureRetreatRoom;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.RoomAbstractClass;
import org.primeTimeHotel.Services.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();







        MasterDAO.close();
    }
}