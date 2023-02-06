package by.bsuir.drugstore.service;

import by.bsuir.drugstore.dto.CreateItemDto;
import by.bsuir.drugstore.dto.CreatePurchaseDto;
import by.bsuir.drugstore.dto.PurchaseDto;
import by.bsuir.drugstore.mapper.ItemMapper;
import by.bsuir.drugstore.mapper.PurchaseMapper;
import by.bsuir.drugstore.model.Item;
import by.bsuir.drugstore.model.Purchase;
import by.bsuir.drugstore.repository.ItemRepository;
import by.bsuir.drugstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void createPurchase(CreatePurchaseDto createPurchaseDto) {
        orderRepository.save(purchaseMapper.toModel(createPurchaseDto));
    }

    public PurchaseDto findById(Long id) {
        return purchaseMapper.toDto(orderRepository.findById(id).orElseThrow());
    }

    public List<PurchaseDto> findAll() {
        return orderRepository.findAll().stream()
                .map(p -> purchaseMapper.toDto(p))
                .collect(Collectors.toList());
    }

    public List<PurchaseDto> findAllByUserId(long id) {
        return orderRepository.findAll().stream()
                .filter(purchase -> purchase.getUser().getId().equals(id))
                .map(purchase -> purchaseMapper.toDto(purchase))
                .collect(Collectors.toList());
    }

    public List<PurchaseDto> findAllOpenPurchases() {
        return orderRepository.findAll().stream()
                .filter(purchase -> purchase.getStatus().equals("Open"))
                .map(purchase -> purchaseMapper.toDto(purchase))
                .collect(Collectors.toList());
    }

    public List<PurchaseDto> findAllClosePurchases() {
        return orderRepository.findAll().stream()
                .filter(purchase -> purchase.getStatus().equals("Close"))
                .map(purchase -> purchaseMapper.toDto(purchase))
                .collect(Collectors.toList());
    }

    public void confirmPurchase(Long purchaseId) {
        Purchase purchase = orderRepository.findById(purchaseId).orElseThrow();
        purchase.setStatus("Confirmed");
        orderRepository.save(purchase);
    }

    public void deletePurchase(Long id) {
        Purchase purchase = orderRepository.findById(id).orElseThrow();
        for (Item item: purchase.getItems()) {
            itemRepository.deleteById(item.getId());
        }
        orderRepository.deleteById(purchase.getId());
    }
}
