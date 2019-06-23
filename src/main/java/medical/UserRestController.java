package medical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import medical.model.User;
import medical.service.UserService;

@RestController
@RequestMapping("/medicalService/api")
public class UserRestController {
    public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Creating User : {}", user);
        System.out.println("Ok");
        System.out.println(user.getUsername());
        User newUser = userService.createUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }
}
