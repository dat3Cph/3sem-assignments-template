package GLSExercise;

import DAOExercise.DAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

public class PackageDAO {

    private static EntityManagerFactory emf;
    private static PackageDAO instance;

    public static PackageDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new PackageDAO();
        }
        return instance;
    }


    public boolean create(Package in) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(in);
            em.getTransaction().commit();
            return true;
        }
    }

    public Package readByTrackingNr(String in){
        try(var em = emf.createEntityManager()){
            TypedQuery<Package> query = em.createNamedQuery("Package.getByTrackingNr",Package.class);
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
            Package found = em.find(Package.class, id);
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
