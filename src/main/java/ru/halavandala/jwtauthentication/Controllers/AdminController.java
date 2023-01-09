package ru.halavandala.jwtauthentication.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        if (users != null) {
            return ResponseEntity.ok(users);
        } return ResponseEntity.badRequest().body("Invalid request");
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getOneUserByUsername(@PathVariable (name = "username") String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } return ResponseEntity.badRequest().body("Invalid request");
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable (name = "username") String username) {
        User user = userService.findByUsername(username);
        if (user == null ) {
            return ResponseEntity.badRequest().body("User with username: " + username + " not found");
        } userService.delete(username);
        return  ResponseEntity.ok().body("User " + username + " successfully deleted");
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable (name = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User with id: " + id + " not found");
        }   return  ResponseEntity.ok().body("User with id:" + id + " successfully deleted");
    }
}
