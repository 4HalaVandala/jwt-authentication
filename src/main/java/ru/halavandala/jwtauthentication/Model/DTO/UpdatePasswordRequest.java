package ru.halavandala.jwtauthentication.Model.DTO;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
