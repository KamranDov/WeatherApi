package com.crocusoft.weatherapi.controller;

import com.crocusoft.weatherapi.dto.userDto.AuthenticationRequest;
import com.crocusoft.weatherapi.dto.userDto.AuthenticationResponse;
import com.crocusoft.weatherapi.dto.userDto.SignUpRequest;
import com.crocusoft.weatherapi.dto.userDto.UserDto;
import com.crocusoft.weatherapi.service.UserService;
import com.crocusoft.weatherapi.service.jwt.UserDetailsServiceImpl;
import com.crocusoft.weatherapi.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    @PostMapping("authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest
            , HttpServletResponse httpServletResponse) throws BadCredentialsException, DisabledException
            , UsernameNotFoundException
            , IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username and password.");
        } catch (DisabledException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated.");
            return null;
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getPassword());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest) {
        UserDto createUser = userService.registerUser(signUpRequest);
        if (createUser == null) {
            return new ResponseEntity<>("User is not created.", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}