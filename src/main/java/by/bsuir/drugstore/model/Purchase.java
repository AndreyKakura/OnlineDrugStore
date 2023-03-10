package by.bsuir.drugstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "purchases")
@AllArgsConstructor
public class Purchase {

    public Purchase(String status) {
        this.status = status;
    }

    public Purchase(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public Purchase() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    //куда доставлять
    @Column(name = "place")
    private String place;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

}
