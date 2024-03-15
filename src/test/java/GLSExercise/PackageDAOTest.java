package GLSExercise;

import GLSExercisePart2.Package;
import GLSExercisePart2.PackageDAO;
import ThePointExercise.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDAOTest {

    static EntityManagerFactory emf;
    static EntityManager em;
    static GLSExercisePart2.PackageDAO packageDAO;

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
        GLSExercisePart2.Package pack = new GLSExercisePart2.Package("A32BGNs","Svend","Didver", GLSExercisePart2.DeliveryStatus.PENDING);
        GLSExercisePart2.Package pack2 = new GLSExercisePart2.Package("GFSDFJn2","Patrick","Morgens", GLSExercisePart2.DeliveryStatus.PENDING);
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
        GLSExercisePart2.Package pack = new Package("A32BGNs","Svend","Didver", GLSExercisePart2.DeliveryStatus.PENDING);
        assertTrue(packageDAO.updateStatus(pack.getId(), GLSExercisePart2.DeliveryStatus.DELIVERED));

    }

    @Test
    void deleteById() {
        assertTrue(packageDAO.deleteById(1));

    }
}