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

        




    }


}
