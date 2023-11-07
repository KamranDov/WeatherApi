package com.crocusoft.weatherapi.dto.userDto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String password;
    private String email;

}
