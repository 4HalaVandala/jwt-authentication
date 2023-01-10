package ru.halavandala.jwtauthentication.Repos;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.halavandala.jwtauthentication.Model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    User findByUsername(String username);
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    @Modifying
    @Query("UPDATE User u set u.passwordHash =:newPassword WHERE u.username =:username")
    @Transactional
    void updatePassword(@Param(value = "username") String username,@Param(value = "newPassword") String newPassword);
}
