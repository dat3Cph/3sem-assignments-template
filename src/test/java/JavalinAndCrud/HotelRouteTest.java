package JavalinAndCrud;

import JavalinAndCrud.config.ApplicationConfig;
import JavalinAndCrud.config.Routes;
import JavalinAndCrud.controllers.HotelController;
import JavalinAndCrud.controllers.RoomController;
import JavalinAndCrud.daos.HotelDAO;
import JavalinAndCrud.daos.RoomDAO;
import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.dtos.RoomDTO;
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
        Room room2 = new Room(null, 22, 9200);
        Room room3 = new Room(null, 72, 9050);
        hotel.addRoom(room);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
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
    @DisplayName("Test get of hotel")
    void testGetHotel(){
        RestAssured.given()
                .when()
                .get("hotel/1")
                .then()
                .statusCode(200)
                .assertThat().body("name", equalTo("TestHotel")).body("id", equalTo(1))
                .log()
                .all();
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

    @Test
    @DisplayName("Test update of hotel")
    void testPutHotel(){
        RestAssured.given()
                .log()
                .all()
                .contentType("application/json")
                .with()
                .body(new Hotel("New name", "New Address!"))
                .when()
                .put("/hotel/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("New name"))
                .log()
                .all();
    }

    @Test
    @DisplayName("Test deletion of hotel")
    void testDeleteHotel(){
        RestAssured.given()
                .log()
                .all()
                .when()
                .delete("/hotel/1")
                .then()
                .statusCode(200)
                .assertThat().body("name", equalTo("TestHotel"))
                .log()
                .all();
    }

    @Test
    @DisplayName("Test get rooms on hotel by id")
    void testGetRoomsByHotelId(){
        RestAssured.given()
                .log()
                .all()
                .when()
                .get("/hotel/1/rooms")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("Test get all rooms")
    void testGetAllRooms(){
        RestAssured.given()
                .when()
                .get("room")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("Test post of new room")
    void testPostRoom(){
        RestAssured.given()
                .log()
                .all()
                .contentType("application/json")
                .with()
                .body(new RoomDTO(1,44,92050))
                .when()
                .post("room")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(4))
                .log()
                .all();
    }

    @Test
    @DisplayName("Test put of room")
    void testPutRoom(){
        RestAssured.given()
                .log()
                .all()
                .contentType("application/json")
                .with()
                .body(new RoomDTO(1, 0, 0))
                .when()
                .put("room/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1)).body("number", equalTo(0))
                .log()
                .all();
    }

    @Test
    @DisplayName("Test deletion of room")
    void testDeleteRoom(){
        RestAssured.given()
                .log()
                .all()
                .when()
                .delete("room/1")
                .then()
                .statusCode(200)
                .assertThat().body("id", equalTo(1))
                .log()
                .all();
    }
    

}
