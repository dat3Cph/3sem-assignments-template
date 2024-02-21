import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DolphinDAO {

    private static EntityManagerFactory emf;
    private static DolphinDAO instance;

    public static DolphinDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
           emf = emf_;
           instance = new DolphinDAO();
        }
        return instance;
    }

    public void register(Person p){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
    }

    public Person updatePerson(Person p){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Person found = em.find(Person.class, p);
            if(found != null){
                Person person = em.merge(p);
                em.getTransaction().commit();
                return person;
            }
        }
        return null;
    }

    public int getAmountPaid(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Person.getTotalPaid");
            query.setParameter("value",id);
            em.getTransaction().commit();
            return  Integer.parseInt(query.getSingleResult().toString());
        }
    }

    public List<Note> getNotes(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Note> query = em.createNamedQuery("Person.getAllNotesByPerson", Note.class);
            query.setParameter("value", id);
            em.getTransaction().commit();
            return query.getResultList();
        }
    }

    public List<NoteWithNameAndAgeDTO> getAllNotesWithNameAndAges(){
        try(var em = emf.createEntityManager()){
            TypedQuery<NoteWithNameAndAgeDTO> query = em.createNamedQuery("Person.getNotesWithNamesAndAges",NoteWithNameAndAgeDTO.class);
            return query.getResultList();
        }
    }


}
