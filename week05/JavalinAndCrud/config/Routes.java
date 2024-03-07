package JavalinAndCrud.config;

import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.controllers.RoomController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

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

}
