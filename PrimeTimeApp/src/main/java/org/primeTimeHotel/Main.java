package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.AccountDAO;
import org.primeTimeHotel.Database_Objects.MasterDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        Account acc = new GuestAccount("speedyJJ","Fr3kyFa5t","Jimmy","John","(264)264-9264","Jimmy@JimmyJohns.com");
        System.out.println(
                dao.insert(acc)
        );

        MasterDAO.close();
    }
}