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
import by.bsuir.drugstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void createPurchase(CreatePurchaseDto createPurchaseDto) {
        Purchase order = purchaseMapper.toModel(createPurchaseDto);
        orderRepository.save(order);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Long userId = userRepository.findByUsername(auth.getName()).orElseThrow().getId();
        Long lastPurchaseId = 0l;
        for (Purchase purchase: orderRepository.findAll().stream()
                .filter(purchase -> purchase.getUser().getId().equals(userId))
                .collect(Collectors.toList())) {
            if(purchase.getId() > lastPurchaseId) {
                lastPurchaseId = purchase.getId();
            }
        }
        if (lastPurchaseId != 0) {
            for (Item item : itemListDtoToListModel(createPurchaseDto.getListItem(),lastPurchaseId)) {
                itemRepository.save(item);
            }
        }

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

    private List<Item> itemListDtoToListModel(List<CreateItemDto> listDto, Long id) {
        return listDto.stream()
                .map(d -> itemMapper.toModel(d,id))
                .collect(Collectors.toList());

    }

    public List<PurchaseDto> findAllByCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow()
                .getPurchases()
                .stream()
                .map(purchase -> purchaseMapper.toDto(purchase))
                .collect(Collectors.toList());
    }

}
