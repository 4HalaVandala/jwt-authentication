package ru.halavandala.jwtauthentication.Domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.halavandala.jwtauthentication.Services.PersonServiceImpl;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Slf4j
public class AuthSuccessfulHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${jwt.expiration.ms}")
    private final int expTime;
    @Value("${jwt.secret}")
    private final String secret;

    private final PersonServiceImpl personService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthSuccessfulHandler(@Value("${jwt.expiration.ms}") int expTime, @Value("${jwt.secret}") String secret, PersonServiceImpl personService) {
        this.expTime =  expTime;
        this.secret = secret;
        this.personService = personService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = JWT.create().withSubject(personService.getUserByUsername(principal.getUsername()).getEmail())
                .withExpiresAt(Instant.ofEpochSecond(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + expTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-type", "application/json");
        response.getWriter().write("{\"token\": \""+token+"\"}");
    }
}
