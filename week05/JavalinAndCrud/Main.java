package JavalinAndCrud;

import JavalinAndCrud.config.ApplicationConfig;
import JavalinAndCrud.config.HibernateConfig;
import JavalinAndCrud.config.Routes;
import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.controllers.RoomController;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {

        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer().startServer(7007).setExceptionHandling().setRoutes(Routes.getRoutes()).setRoutes(Routes.getSecurityRoutes()).setRoutes(Routes.getSecuredRoutes()).checkSecurityRoles();

        boolean isTesting = false;
        HotelController hotelController = new HotelController(isTesting);
        RoomController roomController = new RoomController(isTesting);
        Hotel hotel1 = new Hotel("Hotel Trivago", "CoolStreet 45", new ArrayList<>());
        Room room1 = new Room(null, 4, 6000);
        Room room2 = new Room(null, 7, 6500);
        Room room3 = new Room(null, 11, 7000);
        hotel1.addRoom(room1);
        hotel1.addRoom(room2);
        hotel1.addRoom(room3);
        hotelController.createHotel(hotel1);


    }




}
