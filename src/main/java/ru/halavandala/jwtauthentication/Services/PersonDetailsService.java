package ru.halavandala.jwtauthentication.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Repos.UserRepo;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    private final PersonServiceImpl personService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person user = personService.getUserByUsername(username);
        return new User(user.getEmail(),user.getUsername(), user.getAuthorities());
    }
}
