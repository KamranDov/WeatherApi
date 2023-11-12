package com.growlab.weatherapi.controller;

import com.growlab.weatherapi.dto.userDto.AuthenticationRequest;
import com.growlab.weatherapi.dto.userDto.AuthenticationResponse;
import com.growlab.weatherapi.dto.userDto.UserDto;
import com.growlab.weatherapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthAPI {

    private final UserService userService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.loginUser(authenticationRequest);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

}