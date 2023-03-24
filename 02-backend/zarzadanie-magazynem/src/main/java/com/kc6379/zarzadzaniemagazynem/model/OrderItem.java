package com.kc6379.zarzadzaniemagazynem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties("orders")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "materialId",
            referencedColumnName = "materialId"
    )
    private Material materialId;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(
            name = "ordersId",
            referencedColumnName = "ordersId"
    )
    private Orders orders;
}
