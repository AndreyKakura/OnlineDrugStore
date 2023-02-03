package by.bsuir.drugstore.dto;

import by.bsuir.drugstore.model.Purchase;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemDto {

    @NotNull(message = "Id should not be null")
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private int count;

    @NotNull(message = "purchase id should not be empty")
    private Long purchaseId;

    @NotNull(message = "Product id should not be empty")
    private Long productId;
}
