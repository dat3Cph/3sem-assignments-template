package Recycling;

import Recycling.config.HibernateConfig;
import Recycling.dao.DriverDAOImpl;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        DriverDAOImpl driverDAO = DriverDAOImpl.getInstance(emf);

        //String result = driverDAO.saveDriver("Jens","Petersen", BigDecimal.valueOf(2500));
        //System.out.println(result);


    }


}
