package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.*;
import by.bsuir.drugstore.exception.BadRequestException;
import by.bsuir.drugstore.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createPurchase(@ModelAttribute @Valid CreatePurchaseDto createPurchaseDto, BindingResult bindingResult,
                                            @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }

        purchaseService.createPurchase(createPurchaseDto);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findPurchaseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(purchaseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> findAllPurchases() {
        return ResponseEntity.ok().body(purchaseService.findAll());
    }

    @GetMapping("find_all_by_user/{id}")
    public ResponseEntity<List<PurchaseDto>> findAllPurchasesByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(purchaseService.findAllByUserId(id));
    }

    @PostMapping()
    public ResponseEntity<?> savePurchase(@RequestBody CreatePurchaseDto createPurchaseDto) {
        purchaseService.savePurchase(createPurchaseDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("confirm/{id}")
    public ResponseEntity<?> confirmPurchase(@PathVariable("id") Long id) {
        purchaseService.confirmPurchase(id);
        return ResponseEntity.ok().build();
    }

}
