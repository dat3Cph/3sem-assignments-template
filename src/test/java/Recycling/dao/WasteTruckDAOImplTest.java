package Recycling.dao;

import Recycling.config.HibernateConfig;
import Recycling.model.Driver;
import Recycling.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WasteTruckDAOImplTest {

    static EntityManagerFactory emf;
    static DriverDAOImpl driverDAO;
    static WasteTruckDAOImpl wasteTruckDAO;
    static List<String> driverIds = new ArrayList<>();
    static List<Integer> truckIds = new ArrayList<>();

    @BeforeAll
    static void beforeAll(){
        emf = HibernateConfig.getEntityManagerFactoryConfigForTesting();
        driverDAO = DriverDAOImpl.getInstance(emf);
        wasteTruckDAO = WasteTruckDAOImpl.getInstance(emf);
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            driverIds.add(driverDAO.saveDriver("John","Doe", BigDecimal.valueOf(2555)));
            driverIds.add(driverDAO.saveDriver("Lars","Tyndskid", BigDecimal.valueOf(600000)));
            driverIds.add(driverDAO.saveDriver("Peter","Ellemand",BigDecimal.valueOf(270000)));
            driverIds.add(driverDAO.saveDriver("Mogens","Storm",BigDecimal.valueOf(543383)));
            em.getTransaction().commit();
        }

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            truckIds.add(wasteTruckDAO.saveWasteTruck("Scania","C442-67-C2",90000));
            truckIds.add(wasteTruckDAO.saveWasteTruck("BMW","Q332-70-G3",12000));
            truckIds.add(wasteTruckDAO.saveWasteTruck("Tesla","M654-23-K2",34000));
            truckIds.add(wasteTruckDAO.saveWasteTruck("Audi","L231-51-G0",500000));
            em.getTransaction().commit();
        }
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(truckIds.get(truckIds.size()-1));
        wasteTruckDAO.setWasteTruckAvailable(wasteTruck, true);
        Driver driver = driverDAO.getDriverById(driverIds.get(driverIds.size()-1));
        wasteTruckDAO.addDriverToWasteTruck(wasteTruck,driver);


    }

    @Test
    void saveWasteTruck() {

        int id = wasteTruckDAO.saveWasteTruck("Ford","F321-5G-M2",54000);
        WasteTruck truck = null;
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            truck = em.find(WasteTruck.class,id);
            em.getTransaction().commit();
        }
        assertEquals(id,truck.getId());

    }

    @Test
    void getWasteTruckById() {
        assertNotNull(wasteTruckDAO.getWasteTruckById(truckIds.get(0)));
        assertEquals(truckIds.get(0),wasteTruckDAO.getWasteTruckById(truckIds.get(0)).getId());

    }

    @Test
    void setWasteTruckAvailable() {
        WasteTruck truck = wasteTruckDAO.getWasteTruckById(1);
        wasteTruckDAO.setWasteTruckAvailable(truck,true);
        truck = wasteTruckDAO.getWasteTruckById(1);
        assertTrue(truck.isAvailable());
    }

    @Test
    void deleteWasteTruck() {
        List<WasteTruck> wasteTrucks = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<WasteTruck> query = em.createQuery("select w from WasteTruck w", WasteTruck.class);
            em.getTransaction().commit();
            wasteTrucks = query.getResultList();
        }

        assertEquals(truckIds.size(), wasteTrucks.size());

        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(1);
        wasteTruckDAO.deleteWasteTruck(wasteTruck.getId());

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<WasteTruck> query = em.createQuery("select w from WasteTruck w", WasteTruck.class);
            em.getTransaction().commit();
            wasteTrucks = query.getResultList();
        }

        assertNotEquals(truckIds.size(), wasteTrucks.size());

    }

    @Test
    void addDriverToWasteTruck() {
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(1);
        assertEquals(0,wasteTruck.getDrivers().size());

        wasteTruckDAO.setWasteTruckAvailable(wasteTruck,true);

        wasteTruckDAO.addDriverToWasteTruck(wasteTruck,driverDAO.getDriverById(driverIds.get(0)));

        assertNotNull(wasteTruck.getDrivers());

        assertEquals(1,wasteTruck.getDrivers().size());


    }

    @Test
    void removeDriverFromWasteTruck() {
        Driver driver = driverDAO.getDriverById(driverIds.get(driverIds.size()-1));
        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(truckIds.get(truckIds.size()-1));
        wasteTruckDAO.removeDriverFromWasteTruck(wasteTruck,driver.getId());

        assertEquals(0, wasteTruck.getDrivers().size());


    }

    @Test
    void getAllAvailableTrucks() {

        assertEquals(1,wasteTruckDAO.getAllAvailableTrucks().size());

    }
}