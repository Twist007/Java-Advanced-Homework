package Spotify.rest;

import Spotify.repository.user.User;
import Spotify.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(@RequestBody final User user) {
        userService.addUser(user);
    }

    @DeleteMapping("/{firstName}")
    public void deleteUser(@PathVariable final String firstName) {

        userService.deleteUser(firstName);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable final long id) {

        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PutMapping
    public void update(@RequestBody final User user) {
        userService.updateUser(user);
    }


}
