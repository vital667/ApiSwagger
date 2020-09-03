package testMySQL.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testMySQL.repository.UserRepository;
import testMySQL.model.User;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserApi {

    private UserRepository userRepository;

    public UserApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @ApiOperation(value = "Test Api", notes = "Check the correct operation of Api and connection")
    @GetMapping(path = "/echo")
    public String getEcho() {
        return "Echo 1.. 2.. 3..";
    }


    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    @ApiOperation(value = "Find user by id", notes = "provide information about student by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@ApiParam(value = "Unique id of user", example = "5") @PathVariable int id) {
        if (userRepository.existsById(id))
            return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ApiOperation(value = "Add new User with JSON", notes = "Add new User with JSON")
    @PostMapping
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/exists/{id}")
    public ResponseEntity<User> existsById(@PathVariable int id) {
        if (userRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/count")
    public long count() {
        return userRepository.count();
    }


    @ApiOperation(value = "Update User with JSON")
    @PutMapping
    public ResponseEntity<User> updateById(@RequestBody User newUser) {
        if (userRepository.findById(newUser.getId()).isPresent()) {
            System.out.println(newUser.getId());
            User user = userRepository.findById(newUser.getId()).get();
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
