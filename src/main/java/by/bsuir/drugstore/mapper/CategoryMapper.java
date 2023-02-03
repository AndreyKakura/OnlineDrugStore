package by.bsuir.drugstore.mapper;

import by.bsuir.drugstore.dto.CategoryDto;
import by.bsuir.drugstore.dto.CreateCategoryDto;
import by.bsuir.drugstore.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toModel(CreateCategoryDto createCategoryDto) {
        return Category.builder()
                .categoryName(createCategoryDto.getName()).build();
    }

    public Category toModel(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .categoryName(categoryDto.getName())
                .build();
    }

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getCategoryName())
                .build();
    }
}
