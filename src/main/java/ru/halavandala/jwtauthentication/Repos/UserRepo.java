package ru.halavandala.jwtauthentication.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.halavandala.jwtauthentication.Model.Person;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Person, Long> {
    public boolean existByUsername(String username);

    public boolean existByEmail(String email);

    public Optional<Person> findByUsername(String username);

    public boolean deleteByUsername(String username);
}
