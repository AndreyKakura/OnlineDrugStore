package by.bsuir.drugstore.service;

import by.bsuir.drugstore.dto.CategoryDto;
import by.bsuir.drugstore.dto.CreateCategoryDto;
import by.bsuir.drugstore.mapper.CategoryMapper;
import by.bsuir.drugstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;
    public void createCategory(CreateCategoryDto createCategoryDto) {

        categoryRepository.save(categoryMapper.toModel(createCategoryDto));
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    public CategoryDto findCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
