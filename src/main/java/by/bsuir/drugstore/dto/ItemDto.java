package by.bsuir.drugstore.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemDto {
    private Long id;

    private String name;

    private String specification;

    private Integer price;

    private Long imageId;
}
