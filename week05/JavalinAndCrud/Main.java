package JavalinAndCrud;

import JavalinAndCrud.config.ApplicationConfig;
import JavalinAndCrud.config.HibernateConfig;
import JavalinAndCrud.config.Routes;
import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {

        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer().startServer(7007).setExceptionHandling().setRoutes(Routes.getRoutes());

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        HotelController hotelController = new HotelController(emf);
        Hotel hotel1 = new Hotel("Hotel Trivago", "CoolStreet 45", new ArrayList<>());
        Room room1 = new Room(null, 4, 6000);
        hotel1.addRoom(room1);
        hotelController.createHotel(hotel1);


    }




}
