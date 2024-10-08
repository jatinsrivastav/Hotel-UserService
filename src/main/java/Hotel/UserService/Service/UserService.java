package Hotel.UserService.Service;

import Hotel.UserService.Entity.User;
import Hotel.UserService.Repository.UserRepo;
import Hotel.UserService.dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


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
        User user = modelMapper.map(userDTO,User.class);
        userRepo.findById(user.getId()).orElseThrow(()-> new EntityNotFoundException("not found"));
       return userRepo.save(user);

    }
}
