package by.bsuir.drugstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "specification")
    private String specification;

    @Column(name = "price")
    private Integer price;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    public void setImage(Image image) {
        image.setItem(this);
        this.image = image;
    }
}
