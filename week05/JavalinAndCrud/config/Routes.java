package JavalinAndCrud.config;

import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.controllers.RoomController;
import JavalinAndCrud.controllers.SecurityController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    static ObjectMapper jsonMapper = new ObjectMapper();

    public static EndpointGroup getRoutes(){
        return () -> {
            path("/hotel", () -> {
                get("/", HotelController.getAllHotels());
                post("/", HotelController.createHotel());
                get("/{id}", HotelController.getHotelById());
                put("/{id}", HotelController.updateHotel());
                delete("/{id}", HotelController.deleteHotel());
                get("/{id}/rooms", RoomController.getRoomsByHotelId());
            });
            path("/room", () -> {
                get("/", RoomController.getAllRooms());
                post("/", RoomController.createRoom());
                get("{id}", RoomController.getRoomById());
                put("{id}", RoomController.updateRoom());
                delete("{id}", RoomController.deleteRoom());
            });
        };
    }

    public static EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                post("/login", SecurityController.login(),Role.ANYONE);
                post("/register", SecurityController.register(),Role.ANYONE);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes(){
        return ()-> {
            path("/protected", ()-> {
               before(SecurityController.authenticate());
               get("/user-demo", ctx -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from USER Protected")), Role.USER);
               get("/admin_demo", ctx -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), Role.ADMIN);
            });
        };
    }


    public enum Role implements RouteRole {
        ANYONE,
        USER,
        ADMIN
    }

}
