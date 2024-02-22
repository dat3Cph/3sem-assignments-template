package Recycling.dao;

import Recycling.config.HibernateConfig;
import Recycling.model.Driver;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOImplTest {

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

    }

    @Test
    void saveDriver() {
        String id = driverDAO.saveDriver("Dorthe","Jensen",BigDecimal.valueOf(70040));
        assertEquals(id,driverDAO.getDriverById(id).getId());
    }

    @Test
    void getDriverById() {
        assertNotNull(driverDAO.getDriverById(driverIds.get(0)));
        assertEquals(driverIds.get(0),driverDAO.getDriverById(driverIds.get(0)).getId());
    }

    @Test
    void updateDriver() {
        Driver testDriver = driverDAO.getDriverById(driverIds.get(0));
        testDriver.setSurname("Yahoo");
        Driver driver = driverDAO.updateDriver(testDriver);
        assertEquals("Yahoo",driver.getSurname());
    }

    @Test
    void deleteDriver() {
        List<Driver> drivers = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }
        assertEquals(driverIds.size(), drivers.size());


        Driver driver = driverDAO.getDriverById(driverIds.get(0));
        driverDAO.deleteDriver(driver.getId());
        driverIds.remove(driver.getId());
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }
        assertEquals(driverIds.size(), drivers.size());
    }

    @Test
    void findAllDriversEmployedAtTheSameYear() {
        List<Driver> drivers = driverDAO.findAllDriversEmployedAtTheSameYear("2024");
        for(Driver d : drivers) {
            assertEquals(2024,d.getEmploymentDate().toLocalDate().getYear());
        }
    }

    @Test
    void fetchAllDriversWithSalaryGreaterThan10000() {
        List<Driver> drivers = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        for(Driver d: drivers){
            assertTrue(d.getSalary().intValueExact() > 10000);
        }
    }

    @Test
    void fetchHighestSalary() {
        List<Driver> drivers = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }

        List<Driver> sortedList = drivers.stream().sorted(new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                if(o1.getSalary().intValueExact() > o2.getSalary().intValueExact()){
                    return 1;
                }else {
                    return -1;
                }
            }
        }).toList();

        double salary = driverDAO.fetchHighestSalary();

        assertEquals(salary,sortedList.get(sortedList.size()-1).getSalary().intValueExact());

    }

    @Test
    void fetchFirstNameOfAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }

        List<String> driverNames = driverDAO.fetchFirstNameOfAllDrivers();

        for(int i = 0; i < drivers.size(); i++){
            assertEquals(drivers.get(i).getName(), driverNames.get(i));
        }


    }

    @Test
    void calculateNumberOfDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }

        long count = driverDAO.calculateNumberOfDrivers();
        assertEquals(count, drivers.size());

    }

    @Test
    void fetchDriverWithHighestSalary() {
        List<Driver> drivers = new ArrayList<>();
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            em.getTransaction().commit();
            drivers = query.getResultList();
        }

        List<Driver> sortedList = drivers.stream().sorted(new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                if(o1.getSalary().intValueExact() > o2.getSalary().intValueExact()){
                    return 1;
                }else {
                    return -1;
                }
            }
        }).toList();

        for (Driver d: sortedList){
            System.out.println(d.getSalary());
        }

        assertEquals(sortedList.get(sortedList.size()-1).getId(), driverDAO.fetchDriverWithHighestSalary().getId());


    }
}