package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.RoomAbstractClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDAO extends MasterDAO{
    private List<RoomAbstractClass> tempRooms;

    public RoomDAO(){super();}

    public RoomAbstractClass getRoom(int roomID){
        if(connection == null) return null;
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE id = ?");
            statement.setInt(1, roomID);
            ResultSet rs= statement.executeQuery();
            List<RoomAbstractClass> rooms = resultSetToRoomList(rs);
            if(rooms.isEmpty()) return null;
            return  rooms.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getAllRooms(){
        List<RoomAbstractClass> rooms = new ArrayList<>();
        if (connection != null) {
            try {
                PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms");
                ResultSet  rs= statement.executeQuery();
                rooms = resultSetToRoomList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return rooms;
    }

    public RoomAbstractClass getByRoomNumber(int roomNumber){
        return tempRooms.stream().filter(room ->
                room.getRoomNumber() == roomNumber).findFirst().orElse(null);
    }

    public List<RoomAbstractClass> getByRoomNumberList(List<Integer> roomNumbers){
        return tempRooms.stream().filter(room -> roomNumbers.contains
                (room.getRoomNumber())).collect(Collectors.toList());
    }

    public List<RoomAbstractClass> getByRoomFloor(int roomFloor){
        return tempRooms.stream().filter(room ->
                room.getFloor() == roomFloor).collect(Collectors.toList());
    }

    public List<RoomAbstractClass> getBySmokingStatus(boolean smokeStat){
        return tempRooms.stream().filter(room ->
                room.isSmokerStatus() == smokeStat).collect(Collectors.toList());
    }

    public List<RoomAbstractClass> getByQualityLevel(RoomAbstractClass.QualityLevel quality){
        return tempRooms.stream().filter(room ->
                room.getQualityLevel().equals(quality)).collect(Collectors.toList());
    }


    private List<RoomAbstractClass> resultSetToRoomList(ResultSet rs) throws SQLException {
        List<RoomAbstractClass> rooms= new ArrayList<>();
        while (rs.next()) {
            RoomAbstractClass room = new RoomAbstractClass();
            room.setId(rs.getInt("id"));
            room.setRoomNumber(rs.getInt("room_number"));
            room.setBeds(rs.getObject("room_beds", RoomAbstractClass.Bed.class));
            room.setFloor(rs.getInt("floor_number"));
            room.setCurrentRate(rs.getDouble("current_rate"));
            room.setSmokerStatus(rs.getBoolean("smoker_status"));
            rooms.add(room);
        }
        return rooms;
    }

}
