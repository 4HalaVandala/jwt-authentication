package ru.halavandala.jwtauthentication.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halavandala.jwtauthentication.Model.DTO.UpdatePasswordRequest;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Service.DefaultUserService;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final DefaultUserService userService;

    @PutMapping("/updatePassword")
    public ResponseEntity userUpdatePassword(@RequestBody UpdatePasswordRequest request) {
    boolean updated = userService.updatePassword(request);
    if (updated) {
        return ResponseEntity.ok().body("Password updated for user with username: " + request.getUsername());
    } return  ResponseEntity.badRequest().body("Username or password incorrect");
    }
}
