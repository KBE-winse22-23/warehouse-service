package com.onlineshop.port.user.controller;

import com.onlineshop.core.domain.model.Product;
import com.onlineshop.core.domain.model.Warehouse;
import com.onlineshop.core.domain.dto.UpdateProductQuantityDto;
import com.onlineshop.core.domain.service.interfaces.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.createWarehouse(warehouse);
    }


    @DeleteMapping("/{warehouseId}")
    public Warehouse deleteWarehouse(@PathVariable("warehouseId") Long warehouseId){
        return warehouseService.deleteWarehouse(warehouseId);
    }

    @PostMapping("/{warehouseId}")
    public Warehouse addProductToWarehouse(@RequestBody Product product, @PathVariable("warehouseId") Long warehouseId){
        return warehouseService.addProductToWarehouse(product, warehouseId);
    }

    @PutMapping("/update")
    public Warehouse updateProductQuantity(@RequestBody UpdateProductQuantityDto updateProductQuantityDto){
            return warehouseService.updateProductQuantity(updateProductQuantityDto);
    }
}
