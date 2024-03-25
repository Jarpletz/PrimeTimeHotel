package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.NatureRetreatRoom;
import org.primeTimeHotel.Domain_Model_Objects.RoomAbstractClass;
import org.primeTimeHotel.Domain_Model_Objects.UrbanEleganceRoom;
import org.primeTimeHotel.Domain_Model_Objects.VintageCharmRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDAO extends MasterDAO{
    private List<RoomAbstractClass> tempRooms;

    public RoomDAO(){super();}

    public boolean insert(RoomAbstractClass room){
        if(getRoom(room.getId())==null){
            try{
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO ROOMS(ROOM_NUMBER, QUALITY_LEVEL, ROOM_TYPE, FLOOR, RATE, SMOKING_STATUS)" +  " VALUES (?,?,?,?,?,?)");
                stmt.setInt(1,room.getRoomNumber());
                stmt.setInt(2, room.getQualityLevel().ordinal());
                stmt.setInt(3,room.getType().ordinal());
                stmt.setInt(4,room.getFloor());
                stmt.setDouble(5,room.getCurrentRate());
                stmt.setBoolean(6,room.isSmokerStatus());
                stmt.executeUpdate();
                return true;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(RoomAbstractClass room){
        if(getRoom(room.getId())==null){
            try{
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE ROOMS SET ROOM_NUMBER=?, QUALITY_LEVEL=?, ROOM_TYPE=?, FLOOR=?, RATE=?, SMOKING_STATUS=? WHERE ID = ?");
                stmt.setInt(1,room.getRoomNumber());
                stmt.setInt(2, room.getQualityLevel().ordinal());
                stmt.setInt(3,room.getType().ordinal());
                stmt.setInt(4,room.getFloor());
                stmt.setDouble(5,room.getCurrentRate());
                stmt.setBoolean(6,room.isSmokerStatus());
                stmt.setInt(7,room.getId());
                stmt.executeUpdate();
                return true;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public RoomAbstractClass getRoom(int roomID){
        if(connection == null) return null;
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE id = ?");
            statement.setInt(1,roomID);
            List<RoomAbstractClass> rooms =fetchRooms(statement);
            return rooms == null || rooms.isEmpty()? null : rooms.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getAllRooms(){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms");
            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RoomAbstractClass getByRoomNumber(int roomNumber){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE ROOM_NUMBER = ? FETCH FIRST ROW ONLY");
            statement.setInt(1,roomNumber);
            List<RoomAbstractClass> rooms =fetchRooms(statement);
            return rooms == null || rooms.isEmpty()? null : rooms.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getByRoomNumberList(List<Integer> roomNumbers){
        try {
            String placeholders = String.join(",", java.util.Collections.nCopies(roomNumbers.size(), "?"));
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE ROOM_NUMBER IN("+ placeholders+")");
            for (int i = 0; i < roomNumbers.size(); i++)
                statement.setInt(i + 1, roomNumbers.get(i));
            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getByRoomFloor(int roomFloor){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE FLOOR = ?");
            statement.setInt(1,roomFloor);
            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getBySmokingStatus(boolean smokeStat){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE SMOKING_STATUS = ?");
            statement.setBoolean(1,smokeStat);
            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getByQualityLevel(RoomAbstractClass.QualityLevel quality){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE QUALITY_LEVEL = ?");
            statement.setInt(1,quality.ordinal());
            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomAbstractClass> getAvailable(List<Integer> notAvailable, int floorNumber){
        try {
            String placeholders = String.join(",", java.util.Collections.nCopies(notAvailable.size(), "?"));
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM rooms WHERE FLOOR = ? AND ROOM_NUMBER NOT IN("+ placeholders+")");
            statement.setInt(1,floorNumber);
            for (int i = 0; i < notAvailable.size(); i++)
                statement.setInt(i + 2, notAvailable.get(i));

            return fetchRooms(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<RoomAbstractClass> fetchRooms(PreparedStatement statement){
        if(connection != null) {
            try {
                ResultSet rs = statement.executeQuery();
                return resultSetToRoomList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private List<RoomAbstractClass> resultSetToRoomList(ResultSet rs) throws SQLException {
        List<RoomAbstractClass> rooms= new ArrayList<>();
        while (rs.next()) {
            RoomAbstractClass room ; //FIXME NEEDS TO BE CHANGED -- Perhaps change Room class to non-abstract or create a instance creations method within the abstract class.
            int floor = rs.getInt("floor");
            if(floor == 1) room = new NatureRetreatRoom();
            else if(floor ==2) room = new VintageCharmRoom();
            else room = new UrbanEleganceRoom();
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
