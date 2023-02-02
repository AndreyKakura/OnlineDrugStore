package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CreateProductDto;
import by.bsuir.drugstore.dto.ProductDto;
import by.bsuir.drugstore.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(CreateProductDto createProductDto) {
        return Product.builder()
                .name(createProductDto.getName())
                .specification(createProductDto.getSpecification())
                .price(createProductDto.getPrice()).build();
    }

    public Product toModel(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .specification(productDto.getSpecification())
                .price(productDto.getPrice())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .specification(product.getSpecification())
                .price(product.getPrice())
                .imageId(product.getImage().getId()).build();
    }
}
