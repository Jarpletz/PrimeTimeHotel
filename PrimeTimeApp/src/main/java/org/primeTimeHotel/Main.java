package org.primeTimeHotel;

import org.primeTimeHotel.Database_Objects.AccountDAO;
import org.primeTimeHotel.Database_Objects.MasterDAO;
import org.primeTimeHotel.Database_Objects.ReservationDAO;
import org.primeTimeHotel.Database_Objects.RoomDAO;
import org.primeTimeHotel.Domain_Model_Objects.*;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.Bed;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.NatureRetreatRoom;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.RoomAbstractClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();

       /* List<RoomAbstractClass> rooms = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        beds.add(new Bed(Bed.BedType.SINGLE));
        beds.add(new Bed(Bed.BedType.DOUBLE));
        beds.add(new Bed(Bed.BedType.QUEEN));


        NatureRetreatRoom natureRetreatRoom = new NatureRetreatRoom(1,100,150.5,beds, false,
                RoomAbstractClass.QualityLevel.ECONOMY, RoomAbstractClass.RoomType.DOUBLE);
        //rooms.add(new NatureRetreatRoom(1,100,150.5,beds, false, RoomAbstractClass.QualityLevel.ECONOMY));
        dao.insert(natureRetreatRoom);
        MasterDAO.close();*/
    }
}