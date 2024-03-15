package DAOExercise;

import jakarta.persistence.EntityManagerFactory;

public class StudentDAOImpl implements StudentDAO {

    private static EntityManagerFactory emf;
    private static StudentDAOImpl instance;

    public static StudentDAOImpl getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new StudentDAOImpl();
        }
        return instance;
    }



    @Override
    public void create(Student in) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(in);
        }
    }

    @Override
    public Student read(int id) {
        try(var em = emf.createEntityManager()){
            Student found = em.find(Student.class, id);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    @Override
    public Student update(Student ob, int id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student found = em.find(Student.class,id);
            if(found != null){
                Student toReturn = em.merge(ob);
                em.getTransaction().commit();
                return toReturn;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student found = em.find(Student.class,id);
            if(found != null){
                em.remove(found);

            }
            em.getTransaction().commit();
        }
    }
}
