package com.kc6379.zarzadzaniemagazynem.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;
    private String orderNumber;
    @CreatedDate
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String comment;
    private Double orderTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "statusId",
            referencedColumnName = "statusId"
    )
    private Status status;
    @OneToMany(
            mappedBy = "orders",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<OrderItem> orderItems = new HashSet<>();

}
