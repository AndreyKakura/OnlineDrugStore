package by.bsuir.drugstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length = 100000000)
    private byte[] bytes;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
