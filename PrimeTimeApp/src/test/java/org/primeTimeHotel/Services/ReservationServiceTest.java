package org.primeTimeHotel.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primeTimeHotel.Domain_Model_Objects.RoomAbstractClass;

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
    void createReservationTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try{
            startDate = (Date) dateFormat.parse("2024-03-18 15:54:37");
            endDate = (Date) dateFormat.parse("2024-03-19 15:54:37");

        }catch(RuntimeException e){
            System.err.println("Error: ");
            e.printStackTrace();
            return;
        }

        List<RoomAbstractClass> avalibleRooms = reservationService.getAvalibleRooms(startDate, endDate, 1);
        //fixmE to be fixed at a later date.

    }
}
