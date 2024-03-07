package JavalinAndCrud.daos;

import JavalinAndCrud.model.Room;

public class RoomDAO extends DAO<Room, Integer>{

    private static RoomDAO instance;

    public static RoomDAO getInstance(){
        if(instance == null){
            instance = new RoomDAO();
        }
        return instance;
    }

    private RoomDAO(){
        super(Room.class);
    }

}
