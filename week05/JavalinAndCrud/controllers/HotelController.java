package JavalinAndCrud.controllers;

import JavalinAndCrud.daos.HotelDAO;
import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class HotelController {

    private static HotelDAO hotelDAO;
    private static ObjectMapper jsonMapper = new ObjectMapper();

    public HotelController(EntityManagerFactory emf){
        hotelDAO = HotelDAO.getInstance(emf);
    }

    public void createHotel(Hotel hotel){
        hotelDAO.create(hotel);
    }

    public static Handler getAllHotels(){
        return ctx -> {
            List<HotelDTO> hotels = hotelDAO.getAll();
            ctx.json(hotels);
        };
    }


}
