package by.bsuir.drugstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductDto {

    @NotNull(message = "Id should not be null")
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Specification should not be empty")
    private String specification;

    @NotNull(message = "Price should not be null")
    private Integer price;

    private String imageUrl;

    @NotNull(message = "Category should not be empty")
    private Long categoryId;
}
