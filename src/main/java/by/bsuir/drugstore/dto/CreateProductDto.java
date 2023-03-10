package by.bsuir.drugstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Specification should not be empty")
    private String specification;

    @NotNull(message = "Price should not be empty")
    private Integer price;

    @NotNull(message = "Category should not be empty")
    private Long categoryId;
}
