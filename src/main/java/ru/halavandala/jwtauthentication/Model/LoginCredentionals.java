package ru.halavandala.jwtauthentication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentionals {
    private String email;
    private String password;
}


