package JavalinAndCrud.controllers;

import JavalinAndCrud.config.HibernateConfig;
import JavalinAndCrud.daos.HotelDAO;
import JavalinAndCrud.dtos.HotelDTO;
import JavalinAndCrud.dtos.RoomDTO;
import JavalinAndCrud.model.Hotel;
import JavalinAndCrud.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelController {

    private static HotelDAO hotelDAO;
    private static ObjectMapper jsonMapper = new ObjectMapper();
    //private static EntityManagerFactory emf;

    public HotelController(){
        //emf = HibernateConfig.getEntityManagerFactoryConfig();
        hotelDAO = HotelDAO.getInstance();
    }

    public void createHotel(Hotel hotel){
        hotelDAO.create(hotel);
    }

    public static Handler getAllHotels(){
        return ctx -> {
            List<HotelDTO> hotels = hotelDAO.getAll().stream().map(x -> new HotelDTO(x.getId(), x.getName(), x.getAddress())).toList();
            ctx.json(hotels);
        };
    }


}
