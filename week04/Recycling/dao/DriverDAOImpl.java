package Recycling.dao;

import Recycling.model.Driver;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
        if(driver != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                Driver found = em.find(Driver.class,driver.getId());
                if(found != null){
                    Driver merged = em.merge(driver);
                    em.getTransaction().commit();
                    return merged;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteDriver(String id) {
        if(id != null || !id.isEmpty() || !id.isBlank()){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                Driver found = em.find(Driver.class,id);
                if(found != null){
                    em.remove(found);
                    em.getTransaction().commit();
                }
            }
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        if(year != null | !year.isBlank() | !year.isEmpty()){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                TypedQuery<Driver> query = em.createNamedQuery("Driver.findAllDriversEmployedAtTheSameYear", Driver.class);
                query.setParameter("value",year);
                em.getTransaction().commit();
                return query.getResultList();
            }
        }
        return null;
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("select d from Driver d where d.salary > 10000",Driver.class);
            em.getTransaction().commit();
            return query.getResultList();
        }
    }

    @Override
    public double fetchHighestSalary() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Driver.findHighestSalary");
            em.getTransaction().commit();
            return Double.parseDouble(query.getSingleResult().toString());
        }
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<String> query = em.createNamedQuery("Driver.findFirstNameOfDrivers",String.class);
            em.getTransaction().commit();
            return query.getResultList();
        }
    }

    @Override
    public long calculateNumberOfDrivers() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Driver.driverCount");
            em.getTransaction().commit();
            return Long.parseLong(query.getSingleResult().toString());
        }
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createNamedQuery("Driver.getDriverWithHighestSalary", Driver.class);
            em.getTransaction().commit();
            return query.getResultList().get(0);
        }
    }
}
