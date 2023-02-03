package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.exception.MapperException;
import by.bsuir.drugstore.model.Item;
import by.bsuir.drugstore.repository.OrderRepository;
import by.bsuir.drugstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    public Item toModel(CreateItemDto itemDto) {
        return Item.builder()
                .count(itemDto.getCount())
                .purchase(orderRepository.findById(itemDto.getPurchaseId()).orElseThrow(MapperException::new))
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(MapperException::new))
                .build();
    }

    public Item toModel(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .count(itemDto.getCount())
                .purchase(orderRepository.findById(itemDto.getPurchaseId()).orElseThrow(MapperException::new))
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(MapperException::new))
                .build();
    }

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .count(item.getCount())
                .productId(item.getProduct().getId())
                .purchaseId(item.getPurchase().getId())
                .build();
    }
}
