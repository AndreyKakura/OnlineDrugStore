package by.bsuir.drugstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class CreatePurchaseDto {

    @NotEmpty(message = "Status should not be empty")
    private String Status;

    @NotNull(message = "Items id should not be empty")
    private List<Long> listItemId = new ArrayList<>();

    @NotEmpty(message = "Place should not be empty")
    private String place;

    @NotNull(message = "Category should not be empty")
    private Long categoryId;
}
