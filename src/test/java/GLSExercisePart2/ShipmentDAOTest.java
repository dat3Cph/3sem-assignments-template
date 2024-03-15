package GLSExercisePart2;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDAOTest {

    private static EntityManagerFactory emf;
    private static ShipmentDAO shipmentDAO;
    private static PackageDAO packageDAO;


    @Test
    void shipPackage() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        shipmentDAO = ShipmentDAO.getInstance(emf);
        packageDAO = PackageDAO.getInstance(emf);
        packageDAO.create(new Package("FDFDGDG","Mogens","Knud",DeliveryStatus.PENDING));
        Package pack = packageDAO.readByTrackingNr("FDFDGDG");
        assertTrue(shipmentDAO.shipPackage(pack,new Location(200,43234,"Wowgadesejt 3"), new Location(900,2342,"gade")));

    }
}