package com.growlab.weatherapi.dto.userDto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;

}
