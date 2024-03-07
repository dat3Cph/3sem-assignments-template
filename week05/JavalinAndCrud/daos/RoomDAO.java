package JavalinAndCrud.daos;

import JavalinAndCrud.model.Room;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public List<Room> getRoomsByHotelId(Integer id){
        try(var em = emf.createEntityManager()){
            TypedQuery<Room> query = em.createQuery("select r from Room r join Hotel h on h.id = r.hotel.id where h.id = :value", Room.class);
            query.setParameter("value", id);
            return query.getResultList();
        }
    }

}
