package com.growlab.weatherapi.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    @NotBlank(message = "Username is required.")
    private String username;

    @Size(min = 5, message = "Password must be at least five characters long")
    private String password;

    @Email(message = "Mail is not valid.")
    private String email;
}
