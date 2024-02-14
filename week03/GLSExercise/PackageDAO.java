package GLSExercise;

import DAOExercise.DAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

public class PackageDAO implements DAO<Package> {

    private static EntityManagerFactory emf;
    private static PackageDAO instance;

    public static PackageDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new PackageDAO();
        }
        return instance;
    }

    @Override
    public void create(Package in) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(in);
            em.getTransaction().commit();
        }
    }

    @Override
    public Package read(int id) {
        return null;
    }

    public Package readByTrackingNr(String in){
        try(var em = emf.createEntityManager()){
            TypedQuery<Package> query = em.createNamedQuery("Package.getByTrackingNr",Package.class);
            query.setParameter("value",in);
            return query.getSingleResult();
        }
    }

    public void updateStatus(int id, DeliveryStatus in){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Package.updateDeliveryStatus");
            query.setParameter("value",in);
            query.setParameter("value2",id);
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public Package update(Package obj, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

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

}
