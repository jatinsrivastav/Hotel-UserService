package Hotel.UserService.Service;

import Hotel.UserService.Entity.User;
import Hotel.UserService.Repository.UserRepo;
import Hotel.UserService.RestDTO.BookingDTO;
import Hotel.UserService.dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    List<BookingDTO> listBookingDTO = new ArrayList<>();


    public User registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepo.save(user);
    }

    public User geuserbyId(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("not found"));

    }

    public List<User> getallUser() {
        return userRepo.findAll();
    }

    public User updateUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepo.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("not found"));
        return userRepo.save(user);

    }

    public User UserBookingwithHotel(Integer hotelId, Integer roomId, Integer userId, BookingDTO bookingDTO) {
        String url = "http://BookingService/bookingService/bookings/hotel/" + hotelId + "/" + roomId;

        //need to put the restriction that one user can not book after certain amount of booking

        //todo roomid is same it booking same room again and again. Need to increase the room number I think. This can be done in  the BookSerrvice


        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("not found"));
        try {

            ResponseEntity<BookingDTO> response = restTemplate.postForEntity(url, bookingDTO, BookingDTO.class);
            BookingDTO body = response.getBody();

            listBookingDTO.add(body);
            System.out.println("body value : " + body.getCity() + ", another :" + body.getType());
            user.setBookingDTOS(listBookingDTO);
            return userRepo.save(user);

        } catch (Exception e) {
            return null;
        }


    }
}
