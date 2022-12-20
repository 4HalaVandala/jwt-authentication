package ru.halavandala.jwtauthentication.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Repos.UserRepo;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{


    private final UserRepo userRepo;
    @Override
    public boolean create(Person person) {
        Person userCreated = userRepo.save(person);
        return userCreated == null ? false : true;
    }

    @Override
    public boolean delete(String username)  {
       boolean userExists = userRepo.existByUsername(username);
      return userExists ? userRepo.deleteByUsername(username) : false;

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
