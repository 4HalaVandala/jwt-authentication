package ru.halavandala.jwtauthentication.Security.jwt;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthException extends AuthenticationException {
    public JwtAuthException(String explanation) {
        super(explanation);
    }

    public JwtAuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
