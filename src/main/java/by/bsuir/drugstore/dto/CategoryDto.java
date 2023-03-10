package by.bsuir.drugstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {

    @NotNull(message = "Id should not be null")
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
}
