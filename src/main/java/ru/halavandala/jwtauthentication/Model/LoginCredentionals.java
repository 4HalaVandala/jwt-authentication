package ru.halavandala.jwtauthentication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class LoginCredentionals {
    private String username;
    private String password;
}


