package Hotel.UserService.Controller;


import Hotel.UserService.Entity.User;
import Hotel.UserService.Service.UserService;
import Hotel.UserService.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userService")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.OK);
    }


   /* @PostMapping("/user/login")
    public ResponseEntity<>*/

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getuserbyId(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.geuserbyId(userId), HttpStatus.OK);

    }


    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getallUser(), HttpStatus.OK);
    }


    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);

    }



    //GET /users/{userId}/bookings  - need to implenmt from booking I think.

}
