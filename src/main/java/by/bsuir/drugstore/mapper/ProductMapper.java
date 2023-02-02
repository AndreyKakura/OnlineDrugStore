package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(CreateItemDto createItemDto) {
        return Product.builder()
                .name(createItemDto.getName())
                .specification(createItemDto.getSpecification())
                .price(createItemDto.getPrice()).build();
    }

    public Product toModel(ItemDto itemDto) {
        return Product.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .specification(itemDto.getSpecification())
                .price(itemDto.getPrice())
                .build();
    }

    public ItemDto toDto(Product product) {
        return ItemDto.builder()
                .id(product.getId())
                .name(product.getName())
                .specification(product.getSpecification())
                .price(product.getPrice())
                .imageId(product.getImage().getId()).build();
    }
}
