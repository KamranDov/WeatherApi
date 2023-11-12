package com.growlab.weatherapi.dto.userDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private String password;
    private String email;
}
