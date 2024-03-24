package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.AccountDAO;
import org.primeTimeHotel.Database_Objects.MasterDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Domain_Model_Objects.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MasterDAO.close();
    }
}