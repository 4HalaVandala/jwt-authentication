package ru.halavandala.jwtauthentication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.DTO.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Model.DTO.UpdatePasswordRequest;
import ru.halavandala.jwtauthentication.Model.Role;
import ru.halavandala.jwtauthentication.Model.Status;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Repos.RoleRepo;
import ru.halavandala.jwtauthentication.Repos.UserRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    Date date = new Date();

    @Override
    public User userRegister(RegisterCredentionals registerCredentionals) {

        if (!existsByEmail(registerCredentionals.getEmail()) && !existsByUsername(registerCredentionals.getUsername())) {
            User user = new User();
            Role roleUser = roleRepo.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);

            user.setUsername(registerCredentionals.getUsername());
            user.setEmail(registerCredentionals.getEmail());
            user.setPasswordHash(passwordEncoder.encode(registerCredentionals.getPassword()));
            user.setRoles(userRoles);
            user.setStatus(Status.ACTIVE);
            user.setCreated(date);
            user.setUpdated(date);

            User registeredUser = userRepo.save(user);

            log.info("IN userRegister - user {} successfully registered", registeredUser);
            return registeredUser;
        }
            return null;
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
            log.info("IN findByUsername - user found by username {}",  username);
        } else log.info("IN findByUsername - user not found with username: {}", username);
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
        log.info("IN delete(Long id) - User successfully deleted with id - {}", id);
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean userExistByEmail = userRepo.existsByEmail(email);
        if (userExistByEmail) {
            log.info("IN existsByEmail - User with email : {} already exists", email);
        }
        return userExistByEmail;
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean userExistsByUsername = userRepo.existsByUsername(username);
        if (userExistsByUsername) {
            log.info("IN existsByUsername - User with username: {} already exists", username);
        }
        return userExistsByUsername;
    }

    @Override
    public void delete(String username) {
        userRepo.deleteByUsername(username);
        log.info("IN delete(String username) - User successfully deleted with username - {}", username);
    }

    @Override
    public boolean updatePassword(UpdatePasswordRequest request) {
        User user = findByUsername(request.getUsername());
        if (user == null) {
            log.info("IN updatePassowrd - User with username: {} not found", request.getUsername());
            return false;
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPasswordHash())) {
            log.info("IN updatePassowrd - passswords doesn't match");
            return false;
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));

        return true;
    }
}
