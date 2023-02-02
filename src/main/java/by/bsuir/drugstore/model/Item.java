package by.bsuir.drugstore.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "items")
@AllArgsConstructor
public class Item {

    public Item(int count, Product product, Purchase purchase ) {
        this.count = count;
        this.purchase = purchase;
        this.product = product;
    }

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchases_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
