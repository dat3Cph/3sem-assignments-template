package GLSExercisePart2;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class ShipmentDAO {

    private static EntityManagerFactory emf;
    private static ShipmentDAO instance;

    public static ShipmentDAO getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new ShipmentDAO();
        }
        return instance;
    }

    public boolean shipPackage(Package p, Location location, Location destination){
        Shipment shipment = new Shipment();
        shipment.setAPackage(p);
        shipment.setSource(location);
        shipment.setDestination(location);
        shipment.setShipmentDate(LocalDate.now());
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();

            if(!em.contains(p)){
                p = em.merge(p);
            }
            p.addShipment(shipment);
            em.persist(shipment);
            em.getTransaction().commit();
            return true;
        }
    }

}
