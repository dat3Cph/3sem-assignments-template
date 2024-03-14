package JavalinAndCrud.daos;

import JavalinAndCrud.config.HibernateConfig;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {

    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfigForTesting();
    static List<Hotel> hotels = new LinkedList<>();
    static List<Room> rooms = new LinkedList<>();
    static HotelDAO hotelDAO = HotelDAO.getInstance(true);
    static RoomDAO roomDAO = RoomDAO.getInstance(true);

    @BeforeAll
    static void setup(){
        Hotel hotel1 = new Hotel("TestHotel", "Testvej 2", new ArrayList<>());
        Room room1 = new Room(null, 2,900);
        Room room2 = new Room(null, 6, 599);
        Room room3 = new Room(null, 1,299);
        Room room4 = new Room(null, 12, 1022);


        hotel1.addRoom(room1);
        hotel1.addRoom(room2);
        hotel1.addRoom(room3);
        hotel1.addRoom(room4);

        hotelDAO.create(hotel1);

        Hotel hotel2 = new Hotel("TestHotel2","Mogensvej 11", new ArrayList<>());
        Room room5 = new Room(null, 29, 655);
        Room room6 = new Room(null, 99, 730);
        Room room7 = new Room(null, 120,400);
        Room room8 = new Room(null, 130, 720);

        hotel2.addRoom(room5);
        hotel2.addRoom(room6);
        hotel2.addRoom(room7);
        hotel2.addRoom(room8);

        hotelDAO.create(hotel2);


        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);


        hotels.add(hotel1);
        hotels.add(hotel2);

    }

    @Test
    void getAll() {
        List<Hotel> result = hotelDAO.getAll();
        assertNotNull(result);
        assertEquals(hotels.size(),result.size());
        assertEquals(hotels.get(0).getName(), result.get(0).getName());
    }

    @Test
    void getById() {
        Hotel found = hotelDAO.getById(1);
        assertNotNull(found);
        assertEquals(hotels.get(0).getId(), found.getId());
    }

    @Test
    void create() {
        Hotel newHotel = new Hotel("newHotel", "NewStreet 5", new ArrayList<>());
        hotelDAO.create(newHotel);
        List<Hotel> fetchedHotel = hotelDAO.getAll();
        assertNotEquals(fetchedHotel.size(), hotels.size());
        assertEquals(fetchedHotel.size(), hotels.size()+1);


    }

    @Test
    void update() {
        Hotel foundHotel = hotelDAO.getById(1);
        foundHotel.setName("updatedName");
        Hotel merged = hotelDAO.update(foundHotel, foundHotel.getId());
        assertEquals(foundHotel.getName(), merged.getName());


    }

    @Test
    void delete() {
        Hotel toDelete = hotelDAO.getById(1);
        List<Hotel> dbHotels = hotelDAO.getAll();
        hotelDAO.delete(toDelete.getId());
        List<Hotel> afterDeleteHotels = hotelDAO.getAll();
        assertNotEquals(hotels.size(), dbHotels);
        assertEquals(hotels.size()-1, afterDeleteHotels.size());
        assertNull(hotelDAO.getById(1));
    }
}