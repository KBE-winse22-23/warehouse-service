package com.onlineshop.core.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long warehouseId;
    private String warehouseName;
    private String warehouseAddress;

    @OneToMany(mappedBy="warehouse", cascade = CascadeType.ALL)
    private List<Product> products;

    public void addProduct(Product product){
        if (products == null){
            products = new ArrayList<>();
            products.add(product);
        }else{
            products.add(product);
        }
    }
}
