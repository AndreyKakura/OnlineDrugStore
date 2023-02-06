package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.CategoryDto;
import by.bsuir.drugstore.dto.CreateCategoryDto;
import by.bsuir.drugstore.dto.CreatePurchaseDto;
import by.bsuir.drugstore.dto.ProductDto;
import by.bsuir.drugstore.exception.BadRequestException;
import by.bsuir.drugstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@ModelAttribute @Valid CreateCategoryDto createCategoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }

        categoryService.createCategory(createCategoryDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(categoryService.findCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

}
