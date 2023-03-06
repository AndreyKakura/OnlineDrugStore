package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateProductDto;
import by.bsuir.drugstore.dto.ProductDto;
import by.bsuir.drugstore.exception.MapperException;
import by.bsuir.drugstore.model.Product;
import by.bsuir.drugstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public Product toModel(CreateProductDto createProductDto) {
        return Product.builder()
                .name(createProductDto.getName())
                .specification(createProductDto.getSpecification())
                .category(categoryRepository.findById(createProductDto.getCategoryId()).orElseThrow(MapperException::new))
                .price(createProductDto.getPrice()).build();
    }

    public Product toModel(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .specification(productDto.getSpecification())
                .price(productDto.getPrice())
                .category(categoryRepository.findById(productDto.getCategoryId()).get())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .specification(product.getSpecification())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .imageUrl("localhost:8080/images/" + product.getImage().getId()).build();
    }
}
