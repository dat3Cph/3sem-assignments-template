package GLSExercise;

import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        PackageDAO packageDAO = PackageDAO.getInstance(emf);
        //packageDAO.create(new Package("AGD4234m","Lars","Peter",DeliveryStatus.PENDING));

        Package pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack);

        packageDAO.updateStatus(1,DeliveryStatus.IN_TRANSIT);
        pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack);

        System.out.println(packageDAO.deleteById(2));


    }
}
