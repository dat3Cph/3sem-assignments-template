import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    EntityManager em = emf.createEntityManager();



}
