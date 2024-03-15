package ThePointExercise;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDAO {

    private static PointDAO instance;
    private static EntityManagerFactory emf;

    public static PointDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new PointDAO();
        }
        return instance;
    }

    // Store 1000 Point objects in the database:
    public boolean persistPoints(){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            for (int i = 0; i < 1000; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();
        }
        return true;
    }

    // Find the number of Point objects in the database:
    public int getPointAmount(){
        try(var em = emf.createEntityManager()){
            Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
            return Integer.parseInt(q1.getSingleResult().toString());
        }
    }

    // Find the average X value:
    public float getAverageX(){
        try(var em = emf.createEntityManager()){
            Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
            return Float.parseFloat(q2.getSingleResult().toString());
        }
    }

    // Retrieve all the Point objects from the database:
    public List<Point> getAllPoints(){
        try(var em = emf.createEntityManager()){
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            return query.getResultList();
        }
    }


}
