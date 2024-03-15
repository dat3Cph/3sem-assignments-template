package GLSExercise;

import GLSExercisePart2.Package;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PackageDAO {

    private static EntityManagerFactory emf;
    private static GLSExercisePart2.PackageDAO instance;

    public static GLSExercisePart2.PackageDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new GLSExercisePart2.PackageDAO();
        }
        return instance;
    }


    public boolean create(GLSExercisePart2.Package in) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(in);
            em.getTransaction().commit();
            return true;
        }
    }

    public GLSExercisePart2.Package readByTrackingNr(String in){
        try(var em = emf.createEntityManager()){
            TypedQuery<GLSExercisePart2.Package> query = em.createNamedQuery("Package.getByTrackingNr", GLSExercisePart2.Package.class);
            query.setParameter("value",in);
            return query.getSingleResult();
        }
    }

    public boolean updateStatus(int id, DeliveryStatus in){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Package.updateDeliveryStatus");
            query.setParameter("value",in);
            query.setParameter("value2",id);
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        }
    }

    public boolean deleteById(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            GLSExercisePart2.Package found = em.find(Package.class, id);
            if(found != null){
                Query query = em.createNamedQuery("Package.deleteById");
                query.setParameter("value",id);
                query.executeUpdate();
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }


    public static void close(){
        emf.close();
    }

}
