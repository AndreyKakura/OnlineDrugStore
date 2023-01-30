package by.bsuir.drugstore.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4, max = 30, message = "Username should be between 4 and 30 characters")
    private String username;

    private String password;

    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;

    @Size(min = 1, max = 30, message = "Surname should be between 1 and 30 characters")
    private String surname;
}
