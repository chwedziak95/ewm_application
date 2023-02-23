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
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersItemId;
    @Nullable
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "ordersId",
            referencedColumnName = "ordersId"
    )
    private Orders ordersId;
    @Nullable
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "internalOrderId",
            referencedColumnName = "internalOrderId"
    )
    private InternalOrder internalOrderId;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "materialId",
            referencedColumnName = "materialId"
    )
    private Material materialId;
    private Long quantity;
}
