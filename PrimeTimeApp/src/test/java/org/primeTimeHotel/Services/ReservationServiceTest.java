package org.primeTimeHotel.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primeTimeHotel.Domain_Model_Objects.GuestAccount;
import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.RoomAbstractClass;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class ReservationServiceTest {
    private ReservationService reservationService;
    @BeforeEach
    void init(){reservationService = new ReservationService();}

    @Test
    void getAvalibleRoomsTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        java.util.Date parsedStartDate;
        java.util.Date parsedEndDate;
        try {
            parsedStartDate = dateFormat.parse("2024-03-18");
            parsedEndDate = dateFormat.parse("2024-03-19");
        }catch(RuntimeException e){
            System.err.println("Error: ");
            e.printStackTrace();
            return;
        }
        startDate = new Date(parsedStartDate.getTime());
        endDate = new Date(parsedEndDate.getTime());


        //fixme there is something wrong with the .getAvalibleRooms() I need august to help figure it out
        List<RoomAbstractClass> avalibleRooms = reservationService.getAvalibleRooms(startDate, endDate, 1);
        assertEquals(4, avalibleRooms.size(), "Expecting there to be no rooms.");

    }

    @Test
    void selectRoomTest() throws ParseException{

    }
}
