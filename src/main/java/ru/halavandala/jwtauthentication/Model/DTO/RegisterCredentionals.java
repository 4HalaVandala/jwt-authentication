package ru.halavandala.jwtauthentication.Model.DTO;

import lombok.Data;

@Data
public class RegisterCredentionals {
    private String email;
    private String password;
    private String username;

}
