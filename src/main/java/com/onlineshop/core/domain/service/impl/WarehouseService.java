package com.onlineshop.core.domain.service.impl;

import com.onlineshop.core.domain.model.Product;
import com.onlineshop.core.domain.model.Warehouse;
import com.onlineshop.core.domain.dto.UpdateProductQuantityDto;
import com.onlineshop.core.domain.service.interfaces.IWarehouseService;
import com.onlineshop.core.domain.service.interfaces.ProductRepository;
import com.onlineshop.core.domain.service.interfaces.WarehouseRepository;
import com.onlineshop.port.user.exception.NotFoundException;
import com.onlineshop.port.user.producer.WarehouseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseProducer warehouseProducer;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse deleteWarehouse(Long warehouseId) throws NotFoundException {
        Optional<Warehouse> getWarehouseFromDB = warehouseRepository.findById(warehouseId);

        if(getWarehouseFromDB.isPresent()){
            warehouseRepository.delete(getWarehouseFromDB.get());
        }else{
            throw new NotFoundException("Warehouse with id " + warehouseId + " not found!");
        }

        return null;
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse addProductToWarehouse(Product product, Long warehouseId) throws NotFoundException {
        Optional<Warehouse> getWarehouseFromDB = warehouseRepository.findById(warehouseId);
        Warehouse warehouse;
        if(getWarehouseFromDB.isPresent()){
           warehouse = getWarehouseFromDB.get();
            product.setWarehouse(warehouse);
            warehouse.addProduct(product);
            warehouseRepository.save(warehouse);
            warehouseProducer.sendProductToProductService(product);
        }else{
            throw new NotFoundException("Warehouse with id " + warehouseId + " not found!");
        }

        return warehouse;
    }

    @Override
    public Warehouse updateProductQuantity(UpdateProductQuantityDto updateProductQuantityDto) throws NotFoundException {
        Optional<Product> getProduct = productRepository.findByProductId(updateProductQuantityDto.getProductId());

        if(getProduct.isPresent() && Objects.equals(getProduct.get().getWarehouse().getWarehouseId(), updateProductQuantityDto.getWarehouseId())){
            getProduct.get().setProductQuantity(updateProductQuantityDto.getProductQuantity());
            productRepository.save(getProduct.get());
            warehouseProducer.updateProductQuantityInProductService(getProduct.get());
        }else{
            throw new NotFoundException("Product or Warehouse not found!");
        }

        return null;
    }
}
