package ru.halavandala.jwtauthentication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCredentionals {
    private String email;
    private String password;

    private String username;

}
