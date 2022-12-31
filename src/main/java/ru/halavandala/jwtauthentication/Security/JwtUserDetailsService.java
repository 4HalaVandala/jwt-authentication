package ru.halavandala.jwtauthentication.Security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Security.jwt.JwtUser;
import ru.halavandala.jwtauthentication.Security.jwt.JwtUserFactory;
import ru.halavandala.jwtauthentication.Service.DefaultUserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final DefaultUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("User with username: {} successfully loaded", username);
        return jwtUser;
    }
}
