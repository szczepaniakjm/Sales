package com.myApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTab {
    private Integer id;
    private Integer customerId;
    private Integer productId;
    private BigDecimal discount;
    private Integer quantity;
}
