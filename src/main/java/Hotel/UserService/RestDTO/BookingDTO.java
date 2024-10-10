package Hotel.UserService.RestDTO;

import lombok.Data;

@Data
public class BookingDTO {
    private Integer id;
    private String hotel;
    private String room;
    private String date;
    private Integer roomid;
    private String city;
    private String type;
    private Integer price;

}
