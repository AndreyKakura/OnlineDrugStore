package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toModel(CreateItemDto createItemDto) {
        return Item.builder()
                .name(createItemDto.getName())
                .specification(createItemDto.getSpecification())
                .price(createItemDto.getPrice()).build();
    }

    public Item toModel(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .specification(itemDto.getSpecification())
                .price(itemDto.getPrice())
                .build();
    }

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .specification(item.getSpecification())
                .price(item.getPrice())
                .imageId(item.getImage().getId()).build();
    }
}
