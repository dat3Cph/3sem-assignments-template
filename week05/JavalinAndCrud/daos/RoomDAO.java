package JavalinAndCrud.daos;

import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.dtos.RoomDTO;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RoomDAO extends DAO<RoomDTO, Room>{

    private static RoomDAO instance;
    private static EntityManagerFactory emf;

    private static RoomDAO getInstance(EntityManagerFactory emf_ ){
        if(instance == null){
            instance = new RoomDAO();
            emf = emf_;
        }
        return instance;
    }

    @Override
    public List<RoomDTO> getAll() {
        try(var em = emf.createEntityManager()){
            TypedQuery<RoomDTO> query = em.createNamedQuery("Room.getAll", RoomDTO.class);
            return query.getResultList();
        }
    }

    @Override
    public RoomDTO getById(int id) {
        try(var em = emf.createEntityManager()){
            Room found = em.find(Room.class, id);
            if(found != null){
                return new RoomDTO(found.getId(), found.getHotel().getId(), found.getNumber(), found.getPrice());
            }
        }
        return null;
    }

    @Override
    public void create(Room in) {
        if(in != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(in);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public Room update(RoomDTO in) {
        if(in.getId() < 0){
            return null;
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Room found = em.find(Room.class, in.getId());
            Hotel foundHotel = em.find(Hotel.class, in.getHotelId());
            Room inRoom = new Room(in.getId(), foundHotel, in.getNumber(), in.getPrice());
            if(found != null){
                Room merged = em.merge(inRoom);
                em.getTransaction().commit();
                return merged;
            }
        }
        return null;
    }

    @Override
    public Room delete(int id) {
        if(id < 0){
            return null;
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Room found = em.find(Room.class, id);
            if(found != null){
                em.remove(found);
                em.getTransaction().commit();
                return found;
            }
        }
        return null;
    }
}

