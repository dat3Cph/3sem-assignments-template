import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DolphinDAOTest {

    private static EntityManagerFactory emf;
    private static DolphinDAO dolphinDAO;

    @BeforeAll
    static void setup(){
        emf = HibernateConfig.getEntityManagerFactoryConfigForTesting();
        dolphinDAO = DolphinDAO.getInstance(emf);
    }

    @Test
    void register() {
        Person person = new Person("Darwin");
        dolphinDAO.register(person);
    }

    @Test
    void updatePerson() {
    }
}