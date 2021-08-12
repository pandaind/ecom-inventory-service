package com.example.demo.inventory.service.impl;

import com.example.demo.inventory.domain.Inventory;
import com.example.demo.inventory.repository.InventoryRepository;
import com.example.demo.inventory.service.InventoryService;
import com.example.demo.inventory.service.dto.InventoryDTO;
import com.example.demo.inventory.service.mapper.InventoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper mapper;

    @Autowired
    public InventoryServiceImpl(InventoryRepository repository, InventoryMapper mapper) {
        this.inventoryRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<InventoryDTO> findAll() {
        log.debug("Request to get all Inventories");
        return this.mapper.toDto(this.inventoryRepository.findAll());
    }

    @Override
    public Optional<InventoryDTO> findBySkuCode(String skuCode) {
        return this.inventoryRepository.findBySkuCode(skuCode).map(this.mapper::toDto);
    }


    @Override
    public Optional<InventoryDTO> findById(Long id) {
        return this.inventoryRepository.findById(id).map(this.mapper::toDto);
    }


    @Override
    public InventoryDTO add(InventoryDTO InventoryDTO) {
        log.debug("Request to save Inventory : {}", InventoryDTO);
        Inventory inventory = this.mapper.toEntity(InventoryDTO);
        inventory = this.inventoryRepository.save(inventory);
        return this.mapper.toDto(inventory);
    }

    @Override
    public InventoryDTO update(InventoryDTO InventoryDTO) {
        log.debug("Request to update Inventory : {}", InventoryDTO);
        Inventory inventory = this.mapper.toEntity(InventoryDTO);
        inventory = this.inventoryRepository.save(inventory);
        return this.mapper.toDto(inventory);
    }

    @Override
    public void delete(String skuCode) {
        log.debug("Request to delete Inventory : {}", skuCode);
        this.inventoryRepository.deleteBySkuCode(skuCode);
    }

    @Override
    public boolean existsBySkuCode(String skuCode) {
        return this.inventoryRepository.existsBySkuCode(skuCode);
    }

    @Override
    public Optional<InventoryDTO> partialUpdate(InventoryDTO inventoryDTO) {
        log.debug("Request to partially update Inventory : {}", inventoryDTO);
        return this.inventoryRepository.findBySkuCode(inventoryDTO.getSkuCode())
                .map(existingInventory -> {
                    mapper.partialUpdate(existingInventory, inventoryDTO);
                    return existingInventory;
                }).map(inventoryRepository::save)
                .map(mapper::toDto);
    }
}
