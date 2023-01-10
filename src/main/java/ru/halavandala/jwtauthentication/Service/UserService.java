package ru.halavandala.jwtauthentication.Service;

import ru.halavandala.jwtauthentication.Model.DTO.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Model.DTO.UpdatePasswordRequest;
import ru.halavandala.jwtauthentication.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User userRegister(RegisterCredentionals registerCredentionals);

    List<User> getAll();

    User findByUsername(String username);

    Optional<User> findById(Long id);

    void delete(Long id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void delete(String username);

    boolean updatePasswordForUser(UpdatePasswordRequest request);
}
