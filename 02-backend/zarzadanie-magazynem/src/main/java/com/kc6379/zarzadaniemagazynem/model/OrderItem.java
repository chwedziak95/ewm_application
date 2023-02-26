package com.kc6379.zarzadaniemagazynem.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "ordersId",
            referencedColumnName = "ordersId"
    )
    private Orders orders;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "materialId",
            referencedColumnName = "materialId"
    )
    private Material materialId;
    private Integer quantity;
}
