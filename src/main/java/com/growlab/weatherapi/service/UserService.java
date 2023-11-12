package com.growlab.weatherapi.service;


import com.growlab.weatherapi.domain.UserEntity;
import com.growlab.weatherapi.dto.userDto.AuthenticationRequest;
import com.growlab.weatherapi.dto.userDto.AuthenticationResponse;
import com.growlab.weatherapi.dto.userDto.UserDto;
import com.growlab.weatherapi.exception.AuthenticationException;
import com.growlab.weatherapi.exception.RegistrationException;
import com.growlab.weatherapi.repository.UserRepository;
import com.growlab.weatherapi.service.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public AuthenticationResponse registerUser(UserDto userDto) {
        try {
            UserEntity userEntity = mapper.map(userDto, UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(userEntity);

            var token = jwtService.createToken(userEntity);

            return AuthenticationResponse.builder().jwt(token).build();
        } catch (Exception e) {
            log.error("Error occurred during user registration: {}", e.getMessage());
            throw new RegistrationException("An error occurred during user registration.");

        }
    }

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserEntity userEntity = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();
            String token = jwtService.createToken(userEntity);
            return AuthenticationResponse.builder().jwt(token).build();
        } catch (Exception e) {
            log.error("Error occurred during user login: {}", e.getMessage());
            throw new AuthenticationException("An error occurred during user login.");

        }
    }

}