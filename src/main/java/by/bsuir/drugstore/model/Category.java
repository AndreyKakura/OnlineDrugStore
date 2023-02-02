package by.bsuir.drugstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
public class Category {

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, String categoryUrl) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryId;

    @Column(name = "name")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
