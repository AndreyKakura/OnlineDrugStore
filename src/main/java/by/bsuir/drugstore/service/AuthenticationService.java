package by.bsuir.drugstore.service;

import by.bsuir.drugstore.config.JwtService;
import by.bsuir.drugstore.dto.AuthenticationRequestDto;
import by.bsuir.drugstore.dto.AuthenticationResponseDto;
import by.bsuir.drugstore.dto.RegistrationRequestDto;
import by.bsuir.drugstore.exception.BadRequestException;
import by.bsuir.drugstore.model.Role;
import by.bsuir.drugstore.model.User;
import by.bsuir.drugstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegistrationRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Person with username " + request.getUsername() + " already exists.");
        }
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword(),
                        userRepository.findByUsername(request.getUsername()).get().getAuthorities()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }
}