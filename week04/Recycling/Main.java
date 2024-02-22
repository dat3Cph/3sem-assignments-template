package Recycling;

import Recycling.config.HibernateConfig;
import Recycling.dao.DriverDAOImpl;
import Recycling.dao.WasteTruckDAOImpl;
import Recycling.model.Driver;
import Recycling.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        DriverDAOImpl driverDAO = DriverDAOImpl.getInstance(emf);
        WasteTruckDAOImpl wasteTruckDAO = WasteTruckDAOImpl.getInstance(emf);

        /////////////////////////////////////////// SAVE DRIVER ///////////////////////////////////////////////////////
        String savedId = driverDAO.saveDriver("Carsten","Soendergaard",BigDecimal.valueOf(25000));
        System.out.println(savedId);

        /////////////////////////////////////////// GET DRIVER BY ID ///////////////////////////////////////////////////////

        Driver driver5 = driverDAO.getDriverById("022224-CS-424D");
        System.out.println(driver5.getName());

        /////////////////////////////////////////// UPDATE DRIVER  ///////////////////////////////////////////////////////

        Driver driver = driverDAO.getDriverById("022224-CS-424D");
        System.out.println(driver.getName()+" "+driver.getSurname());
        driver.setSurname("Vestergaard");
        driverDAO.updateDriver(driver);
        driver = driverDAO.getDriverById("022224-CS-424D");
        System.out.println(driver.getName()+" "+driver.getSurname());

        /////////////////////////////////////////// DELETE DRIVER  ///////////////////////////////////////////////////////

        driverDAO.deleteDriver("022224-CS-424D");

        /////////////////////////////////////////// FIND ALL DRIVERS BY EMPLOYMENT YEAR  ///////////////////////////////////////////////////////

        List<Driver> driversBy2023 = driverDAO.findAllDriversEmployedAtTheSameYear("2023");
        for(Driver d : driversBy2023){
            System.out.println(d.getName()+" "+d.getEmploymentDate());
        }

        /////////////////////////////////////////// GET ALL DRIVERS WITH SALARY HIGHER THAN 10000  ///////////////////////////////////////////////////////

        List<Driver> driversWithSalaryGreaterThan1000 = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        for(Driver d: driversWithSalaryGreaterThan1000){
            System.out.println(d.getName()+" "+d.getSalary());
        }

        /////////////////////////////////////////// FETCH HIGHEST SALARY  ///////////////////////////////////////////////////////

        double highestSalary = driverDAO.fetchHighestSalary();
        System.out.println(highestSalary);

        /////////////////////////////////////////// FETCH FIRST NAMES OF ALL DRIVERS ///////////////////////////////////////////////////////

        List<String> driverNames = driverDAO.fetchFirstNameOfAllDrivers();
        for(String s: driverNames){
            System.out.println(s);
        }

        /////////////////////////////////////////// CALCULATE NUMBER OF DRIVERS ///////////////////////////////////////////////////////

        long numberOfDrivers = driverDAO.calculateNumberOfDrivers();
        System.out.println(numberOfDrivers);

        /////////////////////////////////////////// FETCH DRIVER WITH HIGHEST SALARY ///////////////////////////////////////////////////////

        Driver highestPaid = driverDAO.fetchDriverWithHighestSalary();
        System.out.println(highestPaid.getName());

        /////////////////////////////////////////// SAVE WASTETRUCK ///////////////////////////////////////////////////////

        int id = wasteTruckDAO.saveWasteTruck("Scania","Q784-27-G2",29000);
        WasteTruck createdWasteTruck =  wasteTruckDAO.getWasteTruckById(id);
        System.out.println(createdWasteTruck.getBrand());

        /////////////////////////////////////////// GET WASTETRUCK BY ID  ///////////////////////////////////////////////////////

        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(6);
        System.out.println(wasteTruck.getId()+" "+wasteTruck.getBrand());

        /////////////////////////////////////////// SET WASTETRUCK AVAILABLE  ///////////////////////////////////////////////////////

        WasteTruck foundWasteTruck1 = wasteTruckDAO.getWasteTruckById(5);
        wasteTruckDAO.setWasteTruckAvailable(foundWasteTruck1, true);

        foundWasteTruck1 = wasteTruckDAO.getWasteTruckById(5);
        System.out.println(foundWasteTruck1.isAvailable());

        /////////////////////////////////////////// DELETE WASTETRUCK  ///////////////////////////////////////////////////////

        wasteTruckDAO.deleteWasteTruck(6);

        /////////////////////////////////////////// ADD DRIVER TO WASTETRUCK  ///////////////////////////////////////////////////////

        Driver newDriver = driverDAO.getDriverById("022224-LT-800D");
        WasteTruck wasteTruck2 = wasteTruckDAO.getWasteTruckById(7);
        wasteTruckDAO.setWasteTruckAvailable(wasteTruck2,true);
        wasteTruckDAO.addDriverToWasteTruck(wasteTruck2,newDriver);

        /////////////////////////////////////////// REMOVE DRIVER FROM WASTETRUCK  ///////////////////////////////////////////////////////

        Driver removableDriver = driverDAO.getDriverById("022224-LT-800D");
        WasteTruck wasteTruckRemove = wasteTruckDAO.getWasteTruckById(7);
        wasteTruckDAO.removeDriverFromWasteTruck(wasteTruckRemove,removableDriver.getId());

        /////////////////////////////////////////// GET ALL AVAILABLE TRUCKS ///////////////////////////////////////////////////////

        List<WasteTruck> availableTrucks = wasteTruckDAO.getAllAvailableTrucks();
        for(WasteTruck wT : availableTrucks){
            System.out.println(wT.isAvailable());
        }
    }


}
