package ru.halavandala.jwtauthentication.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Model.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Model.Role;
import ru.halavandala.jwtauthentication.Repos.UserRepo;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Person create(RegisterCredentionals registerCredentionals) {
        Person person = new Person();
        if (!userRepo.findByUsername(registerCredentionals.getUsername()).isEmpty()) {
            person.setUsername(registerCredentionals.getUsername());
            if (!userRepo.findByEmail(registerCredentionals.getEmail()).isEmpty()) {
                person.setEmail(registerCredentionals.getEmail());
                person.setPasswordHash(passwordEncoder.encode(registerCredentionals.getPassword()));
                person.setRole("ROLE_USER");
                userRepo.save(person);
            }
        }
       return person;
    }

    @Override
    public boolean delete(String username)  {

      return userRepo.deleteByUsername(username);

    }
    @Override
    public boolean update(String username, String password) {
        return false;
    }

    @Override
    public Person getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
