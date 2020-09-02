package testMySQL;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class UserApi {

    private UserRepository userRepository;

    public UserApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @ApiOperation(value = "Test Api", notes="Check the correct operation of Api and connection")
    @GetMapping(path = "/echo")
    public String getEcho() {
        return "Echo 1.. 2.. 3..";
    }


    @GetMapping
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    @ApiOperation(value = "Find user by id", notes="provide information about student by id")
    @GetMapping("/{id}")
    public @ResponseBody
    Optional<User> getUserById(@ApiParam(value = "unique id of user", example = "5") @PathVariable int id) {
        return userRepository.findById(id);
    }


    @PostMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "saved";
    }


    @PostMapping("/addjson")
    public @ResponseBody
    String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "saved";
    }


    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    String deleteById(@PathVariable int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "deleted";
        } else {
            return "doesn't exist";
        }
    }


    @GetMapping("/exists/{id}")
    public @ResponseBody
    String existsById(@PathVariable int id) {
        if (userRepository.findById(id).isPresent())
            return ("exists");
        else return ("doesn't exist");
    }


    @GetMapping("/count")
    public @ResponseBody
    long count() {
        return userRepository.count();
    }


    @PutMapping("/updatejson/{id}")
    public @ResponseBody
    String updateById(@PathVariable int id, @RequestBody User newUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
            return "user with id=" + id + " was updated";
        } else {
            return "user with id=" + id + " doesn't exist";
        }
    }


    @PutMapping("/update")
    public @ResponseBody
    String updateById(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
            return "user with id=" + id + " was updated";
        } else {
            return "user with id=" + id + " doesn't exist";
        }
    }
}
