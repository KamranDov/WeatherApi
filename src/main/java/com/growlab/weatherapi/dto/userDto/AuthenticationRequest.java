package com.growlab.weatherapi.dto.userDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "Username is required.")
    private String username;

    @Size(min = 5, message = "Password must be at least five characters long")
    private String password;

}
