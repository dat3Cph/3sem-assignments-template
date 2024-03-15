package ThePointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PointDAOTest {

    static EntityManagerFactory emf;
    static EntityManager em;
    static PointDAO pointDAO;

    @BeforeAll
    static void beforeAll(){
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        pointDAO = PointDAO.getInstance(emf);
    }

    @AfterAll
    static void afterAll(){
        emf.close();
        em.close();
    }

    @Test
    void persistPoints() {
        assertTrue(pointDAO.persistPoints());
    }

    @Test
    void getPointAmount() {
        assertEquals(1000,pointDAO.getPointAmount());
    }

    @Test
    void getAverageX() {
        assertEquals(499.5,pointDAO.getAverageX());
    }

    @Test
    void getAllPoints() {
        assertNotNull(pointDAO.getAllPoints());
        assertEquals(1000,pointDAO.getAllPoints().size());
    }
}