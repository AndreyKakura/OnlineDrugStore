package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.CreatePurchaseDto;
import by.bsuir.drugstore.dto.PurchaseDto;
import by.bsuir.drugstore.model.Item;
import by.bsuir.drugstore.model.Purchase;
import by.bsuir.drugstore.repository.ItemRepository;
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

    public Purchase toModel(CreatePurchaseDto createCategoryDto) {
        return Purchase.builder()
                .status(createCategoryDto.getStatus())
                .place(createCategoryDto.getPlace())
                .items(itemListDtoToListModel(createCategoryDto.getListItem()))
                .build();
    }

    public Purchase toModel(PurchaseDto purchaseDto) {
        return Purchase.builder()
                .id(purchaseDto.getId())
                .status(purchaseDto.getStatus())
                .place(purchaseDto.getPlace())
                .items(findItemsByListDto(purchaseDto.getListItemId()))
                .build();
    }


    public PurchaseDto toDto(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .status(purchase.getStatus())
                .listItemId(purchase.getItems().stream().map(Item::getId).collect(Collectors.toList()))
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

    private List<Item> itemListDtoToListModel(List<CreateItemDto> listDto) {
       return listDto.stream()
               .map(d -> itemMapper.toModel(d))
               .collect(Collectors.toList());

    }
}