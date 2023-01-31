package by.bsuir.drugstore.service;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.mapper.ItemMapper;
import by.bsuir.drugstore.model.Image;
import by.bsuir.drugstore.model.Item;
import by.bsuir.drugstore.repository.ImageRepository;
import by.bsuir.drugstore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;

    public void createItem(CreateItemDto createItemDto, MultipartFile file) {
        Image image = new Image();
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Item item = itemMapper.toModel(createItemDto);
        item.setImage(image);
        itemRepository.save(item);
    }

    public ItemDto findById(Long id) {
        return itemMapper.toDto(itemRepository.findById(id).orElseThrow());
    }

    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateItem(ItemDto itemDto, MultipartFile file) {
        Image image = imageRepository.findById(itemDto.getImageId()).orElseThrow();

        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Item updatedItem = itemMapper.toModel(itemDto);

        updatedItem.setImage(image);

        itemRepository.save(updatedItem);
    }
}
