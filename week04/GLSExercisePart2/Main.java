package GLSExercisePart2;

import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        GLSExercisePart2.PackageDAO packageDAO = PackageDAO.getInstance(emf);
        packageDAO.create(new Package("AGD4234m","Lars","Peter",DeliveryStatus.PENDING));

        Package pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack.getId());

        packageDAO.updateStatus(1, GLSExercisePart2.DeliveryStatus.IN_TRANSIT);
        pack = packageDAO.readByTrackingNr("AGD4234m");
        System.out.println(pack.getId());

        System.out.println(packageDAO.deleteById(2));

        packageDAO.create(new Package("GHGFD3423","Peter","Lars", DeliveryStatus.PENDING));
        Package newPack = packageDAO.readByTrackingNr("GHGFD3423");
        packageDAO.shipPackage(newPack,new Location(200,230,"Coolstreet 4"),new Location(400,2330,"CoolStreet  5"));

    }
}
