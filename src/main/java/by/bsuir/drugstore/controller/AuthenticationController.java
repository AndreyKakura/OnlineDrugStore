package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.AuthenticationRequestDto;
import by.bsuir.drugstore.dto.AuthenticationResponseDto;
import by.bsuir.drugstore.dto.RegistrationRequestDto;
import by.bsuir.drugstore.service.AuthenticationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody RegistrationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody @Valid AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/admin")
//    @RolesAllowed("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Map<String, String> admin() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return Map.of("this", "is test");
    }
}
