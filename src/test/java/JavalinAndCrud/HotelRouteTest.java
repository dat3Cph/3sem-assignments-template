package JavalinAndCrud;

import JavalinAndCrud.config.ApplicationConfig;
import JavalinAndCrud.config.Routes;
import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.controllers.RoomController;
import JavalinAndCrud.daos.HotelDAO;
import JavalinAndCrud.daos.RoomDAO;
import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;

public class HotelRouteTest {

    static ApplicationConfig app = ApplicationConfig.getInstance();

    static HotelController hotelController;
    static RoomController roomController;

    @BeforeAll
    static void setupAll(){
        RestAssured.baseURI = "http://localhost:7777/api";
        app.initiateServer().startServer(7777).setExceptionHandling().setRoutes(Routes.getRoutes());
        hotelController = new HotelController(true);
        roomController = new RoomController(true);
        HotelDAO hotelDAO = HotelDAO.getInstance(true);
        RoomDAO roomDAO = RoomDAO.getInstance(true);
        Hotel hotel = new Hotel("TestHotel", "megasejvej 6", new ArrayList<>());
        Room room = new Room(null, 32, 9000);
        hotel.addRoom(room);
        hotelDAO.create(hotel);



    }

    @AfterAll
    static void afterAll(){
        app.closeServer();
    }

    @Test
    @DisplayName("Test that server is running")
    void testServer(){
        RestAssured.given().when().get("hotel").then().statusCode(200).log().all();
    }

    @Test
    @DisplayName("Test creation of hotel")
    void testCreateHotel(){
        RestAssured.given()
                .log()
                .all()
                .contentType("application/json")
                .with()
                .body(new HotelDTO("New Hotel", "CreatedStreet 54"))
                .when()
                .post("hotel")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("New Hotel"))
                .log()
                .all();
    }
}
