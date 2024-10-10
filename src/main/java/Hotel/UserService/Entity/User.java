package Hotel.UserService.Entity;

import Hotel.UserService.Converter.BookingDTOListConverter;
import Hotel.UserService.RestDTO.BookingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @Convert(converter = BookingDTOListConverter.class)
    private List<BookingDTO> bookingDTOS;

}
