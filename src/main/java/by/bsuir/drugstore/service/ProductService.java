package by.bsuir.drugstore.service;

import by.bsuir.drugstore.dto.CreateProductDto;
import by.bsuir.drugstore.dto.ProductDto;
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

    public void createProduct(CreateProductDto createProductDto, MultipartFile file) {
        Image image = new Image();
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product product = productMapper.toModel(createProductDto);
        product.setImage(image);
        productRepository.save(product);
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow());
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateProduct(ProductDto productDto, MultipartFile file) {

        Product productFromDb = productRepository.findById(productDto.getId()).get();
        Product updatedProduct = productMapper.toModel(productDto);

        Image image = imageRepository.findById(productFromDb.getImage().getId()).orElseThrow();

        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        updatedProduct.setImage(image);

        productRepository.save(updatedProduct);
    }

    public List<ProductDto> findAllByCategory(Long id) {
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().getId().equals(id))
                .map(product -> productMapper.toDto(product))
                .collect(Collectors.toList());

    }


}
