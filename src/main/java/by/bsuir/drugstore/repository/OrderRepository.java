package by.bsuir.drugstore.repository;

import by.bsuir.drugstore.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase, Long> {
}
