package JavalinAndCrud.dtos;


import JavalinAndCrud.model.Room;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class HotelDTO {

    private int id;
    private String name;
    private String address;
    private List<RoomDTO> rooms;

    public HotelDTO(int id, String name, String address, List<RoomDTO> rooms){
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    };

    public HotelDTO(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
}
