package com.ep.ecom_api.auth;

import com.ep.ecom_api.config.JwtService;
import com.ep.ecom_api.entity.LoginDetails;
import com.ep.ecom_api.entity.Role;
import com.ep.ecom_api.repository.LoginDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
private final LoginDetailsRepo loginDetailsRepo;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= LoginDetails.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
    loginDetailsRepo.save(user);
    var jwtToken=jwtService.generateToken(user);
    return AuthenticationResponse
            .builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Received Authentication Request:");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.password)
        );
        var user=loginDetailsRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


}
