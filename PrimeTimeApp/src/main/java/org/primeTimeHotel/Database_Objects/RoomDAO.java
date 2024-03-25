package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.RoomClasses.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomDAO extends RootDAO<RoomAbstractClass> {
    public RoomDAO() {
        super("rooms", new String[] {"room_number", "quality_level", "room_type", "floor", "rate", "smoking_status"});
    }
    @Override
    protected void setStatement(PreparedStatement statement, RoomAbstractClass room, int parameterIndex) throws SQLException {
        statement.setInt(parameterIndex++, room.getRoomNumber());
        statement.setInt(parameterIndex++, room.getQualityLevel().ordinal());
        statement.setInt(parameterIndex++, room.getType().ordinal());
        statement.setInt(parameterIndex++, room.getFloor());
        statement.setDouble(parameterIndex++, room.getCurrentRate());
        statement.setBoolean(parameterIndex++, room.isSmokerStatus());
        if (room.getId() != -1)
            statement.setInt(parameterIndex, room.getId());
    }
    @Override
    protected RoomAbstractClass initializeEntry(ResultSet resultSet) throws SQLException {
        int floor = resultSet.getInt("floor");
        RoomAbstractClass room = switch(floor) {
            case 1 -> new NatureRetreatRoom();
            case 2 -> new VintageCharmRoom();
            case 3 -> new UrbanEleganceRoom();
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };

        room.setId(resultSet.getInt("id"));
        room.setFloor(floor);
        room.setRoomNumber(resultSet.getInt("room_number"));
        room.setCurrentRate(resultSet.getDouble("rate"));
        room.setSmokerStatus(resultSet.getBoolean("smoking_status"));

        // Create an array of Bed objects based on the values from the database
        ArrayList<Bed> beds = new ArrayList<>();
        int num = resultSet.getInt("num_single_beds");
        beds.addAll(java.util.Collections.nCopies(num, new Bed(Bed.BedType.SINGLE)));
        num = resultSet.getInt("num_double_beds");
        beds.addAll(java.util.Collections.nCopies(num, new Bed(Bed.BedType.DOUBLE)));
        num = resultSet.getInt("num_queen_beds");
        beds.addAll(java.util.Collections.nCopies(num, new Bed(Bed.BedType.QUEEN)));
        room.setBeds(beds);

        return room;
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
            String placeholders = String.join(",", Collections.nCopies(roomNumbers.size(), "?"));
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
            String placeholders = String.join(",", Collections.nCopies(notAvailable.size(), "?"));
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE FLOOR = ? AND ID NOT IN (" + placeholders + ")");
            statement.setInt(1, floorNumber);


            for (int i = 0; i < notAvailable.size(); i++) {
                statement.setInt(i + 2, notAvailable.get(i));
            }

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
            RoomAbstractClass room;
            int floor = rs.getInt("floor");
            if (floor == 1) {
                room = new NatureRetreatRoom();
            } else if (floor == 2) {
                room = new VintageCharmRoom();
            } else {
                room = new UrbanEleganceRoom();
            }

            room.setId(rs.getInt("id"));
            room.setFloor(floor);
            room.setRoomNumber(rs.getInt("room_number"));

            // Create an array of Bed objects based on the values from the database
            int numSingleBeds = rs.getInt("num_single_beds");
            int numDoubleBeds = rs.getInt("num_double_beds");
            int numQueenBeds = rs.getInt("num_queen_beds");
            ArrayList<Bed> beds = new ArrayList<>();
            for (int i = 0; i < numSingleBeds; i++) {
                beds.add(new Bed(Bed.BedType.SINGLE));
            }
            for (int i = 0; i < numDoubleBeds; i++) {
                beds.add(new Bed(Bed.BedType.DOUBLE));
            }
            for (int i = 0; i < numQueenBeds; i++) {
                beds.add(new Bed(Bed.BedType.QUEEN));
            }
            room.setBeds(beds);

            room.setFloor(floor);
            room.setCurrentRate(rs.getDouble("rate"));
            room.setSmokerStatus(rs.getBoolean("smoking_status"));
            rooms.add(room);
        }
        return rooms;
    }

}
