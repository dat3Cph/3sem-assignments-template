package GLSExercise;

import ThePointExercise.HibernateConfig;
import ThePointExercise.PointDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDAOTest {

    static EntityManagerFactory emf;
    static EntityManager em;
    static PackageDAO packageDAO;

    @BeforeEach
    void beforeEach(){
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        packageDAO = PackageDAO.getInstance(emf);
    }

    @AfterAll()
    static void afterAll(){
    }


    @Test
    void create() {
        Package pack = new Package("A32BGNs","Svend","Didver",DeliveryStatus.PENDING);
        Package pack2 = new Package("GFSDFJn2","Patrick","Morgens",DeliveryStatus.PENDING);
        assertNotNull(packageDAO.create(pack));
        assertTrue(packageDAO.create(pack2));
    }

    @Test
    void readByTrackingNr() {
        assertEquals("A32BGNs",packageDAO.readByTrackingNr("A32BGNs").getTrackingNumber());
        assertNotEquals("totaltenNr", packageDAO.readByTrackingNr("A32BGNs").getTrackingNumber());
        assertNotNull(packageDAO.readByTrackingNr("A32BGNs"));
    }

    @Test
    void updateStatus() {
        Package pack = new Package("A32BGNs","Svend","Didver",DeliveryStatus.PENDING);
        assertTrue(packageDAO.updateStatus(pack.getId(),DeliveryStatus.DELIVERED));

    }

    @Test
    void deleteById() {
        assertTrue(packageDAO.deleteById(1));

    }
}