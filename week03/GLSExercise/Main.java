package GLSExercise;

import GLSExercisePart2.HibernateConfig;
import GLSExercisePart2.Package;
import GLSExercisePart2.PackageDAO;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        GLSExercisePart2.PackageDAO packageDAO = PackageDAO.getInstance(emf);
        //packageDAO.create(new Package("AGD4234m","Lars","Peter",DeliveryStatus.PENDING));

        Package pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack);

        packageDAO.updateStatus(1, GLSExercisePart2.DeliveryStatus.IN_TRANSIT);
        pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack);

        System.out.println(packageDAO.deleteById(2));


    }
}
