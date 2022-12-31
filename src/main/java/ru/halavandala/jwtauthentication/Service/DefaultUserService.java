package ru.halavandala.jwtauthentication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.Role;
import ru.halavandala.jwtauthentication.Model.Status;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Repos.RoleRepo;
import ru.halavandala.jwtauthentication.Repos.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService implements UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User userRegister(User user) {
        Role roleUser = roleRepo.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepo.save(user);

        log.info("IN register - user {} successfully registered", registeredUser);


        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepo.findAll();
        log.info("IN getAll - {} users found", allUsers.size());
        return allUsers;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepo.findByUsername(username);
        if (result != null) {
            log.info("IN findByUsername - user: {} found by username {}", result.hashCode(), username);
        } else log.info("IN findByUsername - user not found with username: {}", username);
        return result;
    }

    @Override
    public void delete(Long id) {
    userRepo.deleteById(id);
    log.info("User successfully deleted with id - {}", id);
    }
}
