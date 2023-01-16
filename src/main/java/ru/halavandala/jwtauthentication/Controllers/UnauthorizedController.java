package ru.halavandala.jwtauthentication.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.halavandala.jwtauthentication.Model.DTO.LoginCredentionals;
import ru.halavandala.jwtauthentication.Model.DTO.RegisterCredentionals;
import ru.halavandala.jwtauthentication.Model.User;
import ru.halavandala.jwtauthentication.Security.jwt.JwtTokenProvider;
import ru.halavandala.jwtauthentication.Service.DefaultUserService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@Api(description = "UnauthorizedController")
public class UnauthorizedController {

    private final AuthenticationManager authenticationManager;
    private final DefaultUserService defaultUserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final DefaultUserService userService;


    @PostMapping("/login")
    @ApiOperation("Login page")
    public ResponseEntity<?> login(@RequestBody LoginCredentionals loginCredentionals) {
        try {
            String username = loginCredentionals.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, loginCredentionals.getPassword())
            );
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info(String.valueOf(e));
            throw new BadCredentialsException("Invalid username or password");

        }
    }
    @PostMapping("/register")
    @ApiOperation("Register for users")
    public ResponseEntity registerUser(@RequestBody RegisterCredentionals registerCredentionals) {
        User user = userService.userRegister(registerCredentionals);
        if (user != null) {
            return ResponseEntity.ok().body("User successfully registered: " + user);
        } return ResponseEntity.badRequest().body("User already exist");

    }
}