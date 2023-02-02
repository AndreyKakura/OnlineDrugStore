package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.ItemDto;
import by.bsuir.drugstore.exception.BadRequestException;
import by.bsuir.drugstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createItem(@ModelAttribute @Valid CreateItemDto createItemDto, BindingResult bindingResult,
                                        @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }
        productService.createItem(createItemDto, file);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAllItems() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PatchMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateItem(@ModelAttribute @Valid ItemDto itemDto, BindingResult bindingResult,
                                        @RequestParam MultipartFile file) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }

        productService.updateItem(itemDto, file);

        return ResponseEntity.ok().build();
    }
}
