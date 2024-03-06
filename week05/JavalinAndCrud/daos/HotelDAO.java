package JavalinAndCrud.daos;

import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.model.Hotel;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HotelDAO extends DAO<HotelDTO, Hotel>{

    private static HotelDAO instance;
    private static EntityManagerFactory emf;

    public static HotelDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            instance = new HotelDAO();
            emf = emf_;
        }
        return instance;
    }

    @Override
    public List<HotelDTO> getAll() {
        try(var em = emf.createEntityManager()){
            TypedQuery<HotelDTO> query = em.createNamedQuery("Hotel.getAll", HotelDTO.class);
            return query.getResultList();
        }
    }

    @Override
    public HotelDTO getById(int id) {
        try(var em = emf.createEntityManager()){
            Hotel found = em.find(Hotel.class,id);
            if(found != null){
                return new HotelDTO(found.getId(), found.getName(),found.getAddress(),found.getRooms());
            }
        }
        return null;
    }

    @Override
    public void create(Hotel en) {
        if(en != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(en);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public Hotel update(HotelDTO in) {
        Hotel hotel = new Hotel(in.getId(),in.getName(),in.getAddress(),in.getRooms());
        if(in.getId() < 0){
            return null;
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Hotel found = em.find(Hotel.class, hotel.getId());
            if(found != null){
                Hotel merged = em.merge(hotel);
                em.getTransaction().commit();
                return merged;
            }
        }
        return null;
    }

    @Override
    public Hotel delete(int id) {
        if(id < 0){
            return null;
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Hotel found = em.find(Hotel.class, id);
            if(found != null){
                em.remove(found);
                em.getTransaction().commit();
                return found;
            }
        }
        return null;
    }
}
