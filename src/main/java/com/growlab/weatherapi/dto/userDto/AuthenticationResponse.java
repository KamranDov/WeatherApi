package com.growlab.weatherapi.dto.userDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

    private String jwt;
}
