package ru.halavandala.jwtauthentication.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halavandala.jwtauthentication.Model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
