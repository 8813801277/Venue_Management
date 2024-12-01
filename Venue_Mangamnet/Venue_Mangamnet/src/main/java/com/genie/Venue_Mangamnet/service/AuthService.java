package com.genie.Venue_Mangamnet.service;

import com.genie.Venue_Mangamnet.config.SecurityConig;
import com.genie.Venue_Mangamnet.dto.LoginDto;
import com.genie.Venue_Mangamnet.dto.UserDto;
import com.genie.Venue_Mangamnet.dto.UserRegistrationDto;
import com.genie.Venue_Mangamnet.entity.User;
import com.genie.Venue_Mangamnet.repo.UserRepository;
import com.genie.Venue_Mangamnet.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public UserDto registerUser(UserRegistrationDto registrationDto) {

        if(userRepository.existsByUsername(registrationDto.getUsername())){
            throw new SecurityConig.UserAlreadyExistsException("Username is already taken!");
        }

        if(userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new SecurityConig.UserAlreadyExistsException("Email is already in use!");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());


        switch (user.getRole()) {
            case STUDENT:
                user.setStudentId(generateStudentId());
                break;
            case CLUB_MEMBER:
                user.setClubName(registrationDto.getClubName());
                break;
            case ADMIN:
                user.setAdminDepartment(registrationDto.getAdminDepartment());
                break;

        }

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }


    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private String generateStudentId(){
        return "ST" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String authenticateUser(LoginDto loginDto) throws AuthenticationException {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new AuthenticationException("Invalid Username or password"));

        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        // Generate JWT with username and role
        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
}
