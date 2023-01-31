package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.AuthenticationRequestDto;
import by.bsuir.drugstore.dto.AuthenticationResponseDto;
import by.bsuir.drugstore.dto.RegistrationRequestDto;
import by.bsuir.drugstore.exception.BadRequestException;
import by.bsuir.drugstore.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody @Valid RegistrationRequestDto request,
                                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody @Valid AuthenticationRequestDto request,
                                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }

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
