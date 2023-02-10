package com.onlineshop.core.domain.service.interfaces;

import com.onlineshop.core.domain.model.Product;
import com.onlineshop.core.domain.model.Warehouse;
import com.onlineshop.core.domain.dto.UpdateProductQuantityDto;
import com.onlineshop.port.user.exception.NotFoundException;

import java.util.List;


public interface IWarehouseService {

    List<Warehouse> getWarehouses();

    Warehouse deleteWarehouse(Long warehouseId) throws NotFoundException;

    Warehouse createWarehouse(Warehouse warehouse);

    Warehouse addProductToWarehouse(Product product, Long warehouseId) throws NotFoundException;

    Warehouse updateProductQuantity(UpdateProductQuantityDto updateProductQuantityDto) throws NotFoundException;
}
