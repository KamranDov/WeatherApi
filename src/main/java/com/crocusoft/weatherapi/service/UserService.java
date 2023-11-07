package com.crocusoft.weatherapi.service;


import com.crocusoft.weatherapi.domain.UserEntity;
import com.crocusoft.weatherapi.dto.userDto.SignUpRequest;
import com.crocusoft.weatherapi.dto.userDto.UserDto;
import com.crocusoft.weatherapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setEmail(signUpRequest.getEmail());
        UserEntity createdUser = userRepository.save(userEntity);
        UserDto userDto = new UserDto();
        userDto.setUsername(createdUser.getUsername());
        userDto.setPassword(passwordEncoder.encode(createdUser.getPassword()));
        userDto.setEmail(createdUser.getEmail());
        return userDto;
    }

}