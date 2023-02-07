package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreatePurchaseDto;
import by.bsuir.drugstore.dto.PurchaseDto;
import by.bsuir.drugstore.model.Item;
import by.bsuir.drugstore.model.Purchase;
import by.bsuir.drugstore.repository.ItemRepository;
import by.bsuir.drugstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseMapper {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserRepository userRepository;

    public Purchase toModel(CreatePurchaseDto createPurchaseDto) {
        return Purchase.builder()
                .status(createPurchaseDto.getStatus())
                .place(createPurchaseDto.getPlace())
                .user(userRepository.findById(createPurchaseDto.getUserId()).orElseThrow())
                //.items(itemListDtoToListModel(createPurchaseDto.getListItem()))
                .build();
    }

    public PurchaseDto toDto(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .status(purchase.getStatus())
                .listItemDto(purchase.getItems().stream().map(item -> itemMapper.toDto(item)).collect(Collectors.toList()))
                .build();
    }

    private List<Item> findItemsByListDto(List<Long> listId) {
        List<Item> items = new ArrayList<>();
        for (Long id : listId) {
            try {
                items.add(itemRepository.findById(id).orElseThrow(Exception::new));
            } catch (Exception e) {

            }
        }
        return items;
    }


}
