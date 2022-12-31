package ru.halavandala.jwtauthentication.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halavandala.jwtauthentication.Model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
