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
@JsonIgnoreProperties("internalOrder")
public class InternalOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "materialId",
            referencedColumnName = "materialId"
    )
    private Material materialId;
    @ManyToOne()
    @JoinColumn(
            name = "internalOrderId"
    )
    private InternalOrder internalOrder;

    private Integer quantity;
}
