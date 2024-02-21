package Recycling.dao;

import Recycling.model.Driver;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO{

    private static EntityManagerFactory emf;
    private static DriverDAOImpl instance;


    public static DriverDAOImpl getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new DriverDAOImpl();
        }
        return instance;
    }


    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        Driver driver = null;
        if(!name.isEmpty() & !surname.isEmpty()){
            driver = new Driver(name,salary,surname);
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(driver);
            em.getTransaction().commit();
            return driver.getId();
        }
    }

    @Override
    public Driver getDriverById(String id) {
        Driver found = null;
        if(id != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                found = em.find(Driver.class, id);
                em.getTransaction().commit();
                if(found != null){
                    return found;
                }

            }
        }
        return found;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        return null;
    }

    @Override
    public void deleteDriver(String id) {

    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        return null;
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        return null;
    }

    @Override
    public double fetchHighestSalary() {
        return 0;
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        return null;
    }

    @Override
    public long calculateNumberOfDrivers() {
        return 0;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        return null;
    }
}
