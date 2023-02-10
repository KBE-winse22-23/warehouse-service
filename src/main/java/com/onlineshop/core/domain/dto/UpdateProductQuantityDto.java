package com.onlineshop.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateProductQuantityDto {

    private Long warehouseId;
    private Long productId;
    private int productQuantity;
}
