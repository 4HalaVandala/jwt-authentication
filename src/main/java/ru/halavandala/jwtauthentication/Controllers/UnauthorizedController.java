package ru.halavandala.jwtauthentication.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.halavandala.jwtauthentication.Model.LoginCredentionals;
import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Model.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Repos.UserRepo;
import ru.halavandala.jwtauthentication.Services.PersonServiceImpl;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UnauthorizedController {

    private final UserRepo userRepo;
    private final PersonServiceImpl personService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterCredentionals registerCredentionals) {
       personService.create(registerCredentionals);
       return  ResponseEntity.status(HttpStatus.OK).body("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginCredentionals loginCredentionals) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/users")
    public List<Person> usersMapping() {
        return userRepo.findAll();
    }
}
