package com.example.demo.inventory.service;


import com.example.demo.inventory.service.dto.InventoryDTO;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<InventoryDTO> findAll();

    Optional<InventoryDTO> findBySkuCode(String skuCode);

    Optional<InventoryDTO> findById(Long id);

    InventoryDTO add(InventoryDTO inventoryDTO);

    InventoryDTO update(InventoryDTO inventoryDTO);

    void delete(String skuCode);

    boolean existsBySkuCode(String skuCode);

    Optional<InventoryDTO> partialUpdate(InventoryDTO inventoryDTO);
}
