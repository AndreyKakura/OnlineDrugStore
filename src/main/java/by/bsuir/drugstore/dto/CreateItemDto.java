package by.bsuir.drugstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateItemDto {

    @NotEmpty(message = "Name should not be empty")
    private int count;

    @NotNull(message = "Product id should not be empty")
    private Long productId;
}
