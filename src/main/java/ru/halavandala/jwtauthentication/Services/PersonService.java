package ru.halavandala.jwtauthentication.Services;

import ru.halavandala.jwtauthentication.Model.Person;
import ru.halavandala.jwtauthentication.Model.RegisterCredentionals;

import java.util.Optional;

public interface PersonService {
    public boolean create(Person person);

    public boolean delete(String username);

    public boolean update(String username, String password);

    public Person getUserByUsername(String username);
}
