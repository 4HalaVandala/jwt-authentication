package ru.halavandala.jwtauthentication.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Model.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Repos.UserRepo;
import ru.halavandala.jwtauthentication.Services.PersonServiceImpl;

@RestController
@RequiredArgsConstructor
public class UnauthorizedController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final PersonServiceImpl personService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterCredentionals registerCredentionals) {

      Person user =  new Person(registerCredentionals.getUsername(),
                    registerCredentionals.getEmail(),
                    passwordEncoder.encode(registerCredentionals.getPassword()),
              null);
      userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
