package ru.halavandala.jwtauthentication.Service;

import ru.halavandala.jwtauthentication.Model.User;

import java.util.List;

public interface UserService {
    User userRegister(User user);

    List<User> getAll();
    User findByUsername(String username);

    void delete (Long id);
}
