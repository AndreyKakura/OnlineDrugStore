package by.bsuir.drugstore.service;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.mapper.ProductMapper;
import by.bsuir.drugstore.model.Image;
import by.bsuir.drugstore.model.Product;
import by.bsuir.drugstore.repository.ImageRepository;
import by.bsuir.drugstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public void createItem(CreateItemDto createItemDto, MultipartFile file) {
        Image image = new Image();
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product product = productMapper.toModel(createItemDto);
        product.setImage(image);
        productRepository.save(product);
    }

    public ItemDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow());
    }

    public List<ItemDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateItem(ItemDto itemDto, MultipartFile file) {
        Image image = imageRepository.findById(itemDto.getImageId()).orElseThrow();

        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product updatedItem = productMapper.toModel(itemDto);

        updatedItem.setImage(image);

        productRepository.save(updatedItem);
    }
}
