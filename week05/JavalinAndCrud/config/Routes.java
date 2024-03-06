package JavalinAndCrud.config;

import JavalinAndCrud.controllers.HotelController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    public static EndpointGroup getRoutes(){
        return () -> {
            path("/hotel", () -> {
                path("/rooms", () -> {

                });
                get("/", HotelController.getAllHotels());
            });
        };
    }

}
