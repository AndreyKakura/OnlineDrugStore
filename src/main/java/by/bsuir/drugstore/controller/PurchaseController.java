package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.dto.CreatePurchaseDto;
import by.bsuir.drugstore.dto.PurchaseDto;
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
    public ResponseEntity<?> createPurchase(@RequestBody @Valid CreatePurchaseDto createPurchaseDto) {
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

    @GetMapping("/find_open")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PurchaseDto>> findAllOpenPurchases() {
        return ResponseEntity.ok().body(purchaseService.findAllOpenPurchases());
    }

    @GetMapping("/find_close")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PurchaseDto>> findAllClosePurchases() {
        return ResponseEntity.ok().body(purchaseService.findAllClosePurchases());
    }

    @GetMapping("find_all_by_user/{id}")
    public ResponseEntity<List<PurchaseDto>> findAllPurchasesByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(purchaseService.findAllByUserId(id));
    }

    @GetMapping("find_all_by_current_user")
    public ResponseEntity<List<PurchaseDto>> findAllPurchasesByCurrentUser() {
        return ResponseEntity.ok().body(purchaseService.findAllByCurrentUser());
    }


    @PutMapping("confirm/{id}")
    public ResponseEntity<?> confirmPurchase(@PathVariable("id") Long id) {
        purchaseService.confirmPurchase(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePurchaseById(@PathVariable("id") Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.ok().build();
    }
}
