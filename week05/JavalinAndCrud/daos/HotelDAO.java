package JavalinAndCrud.daos;

import JavalinAndCrud.model.Hotel;

public class HotelDAO extends DAO<Hotel, Integer>{


    private static HotelDAO instance;

    public static HotelDAO getInstance(){
        if(instance == null){
            instance = new HotelDAO();
        }
        return instance;
    }

    private HotelDAO(){
        super(Hotel.class);
    }


}
