package Recycling.dao;

import Recycling.model.Driver;
import Recycling.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO{

    private static EntityManagerFactory emf;
    private static WasteTruckDAOImpl instance;


    public static WasteTruckDAOImpl getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new WasteTruckDAOImpl();
        }
        return instance;
    }

    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        WasteTruck wasteTruck = null;
        if(brand != null || !brand.isEmpty() || !brand.isBlank() || registrationNumber != null || !registrationNumber.isBlank() || !registrationNumber.isEmpty() || capacity > 0){
            wasteTruck = new WasteTruck(brand,capacity,registrationNumber);
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(wasteTruck);
                em.getTransaction().commit();
            }
        }
        return wasteTruck.getId();
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        if(id > 0){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                WasteTruck found = em.find(WasteTruck.class, id);
                em.getTransaction().commit();
                if(found != null){
                    return found;
                }
            }
        }
        return null;
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        if(wasteTruck != null){
            wasteTruck.setAvailable(available);
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.merge(wasteTruck);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public void deleteWasteTruck(int id) {
        if(id > 0){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                WasteTruck found = em.find(WasteTruck.class,id);
                if(found != null){
                    em.remove(found);
                    em.getTransaction().commit();
                }
            }
        }
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        if(wasteTruck != null & driver != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();

                WasteTruck foundWT = em.find(WasteTruck.class, wasteTruck.getId());
                Driver foundDR = em.find(Driver.class, driver.getId());

                if(foundWT != null & foundDR != null){
                    if(foundWT.isAvailable()){
                        wasteTruck.addDriver(driver);
                        driver.addTruck(wasteTruck);
                        em.merge(wasteTruck);
                        em.merge(driver);
                        em.getTransaction().commit();
                    }
                }
            }
        }
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        if(wasteTruck != null | !id.isEmpty() | !id.isBlank() | id != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();

                WasteTruck foundWT = em.find(WasteTruck.class,wasteTruck.getId());
                Driver foundDR = em.find(Driver.class, id);

                if(foundWT != null & foundDR != null){
                    wasteTruck.getDrivers().remove(foundDR);
                    foundDR.addTruck(null);
                    em.merge(wasteTruck);
                    em.merge(foundDR);
                    em.getTransaction().commit();
                }

            }
        }
    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<WasteTruck> query = em.createQuery("select w from WasteTruck w where w.isAvailable = true", WasteTruck.class);
            em.getTransaction().commit();
            return query.getResultList();
        }
    }
}
