package com.example.demo.inventory.repository;

import com.example.demo.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
    void deleteBySkuCode(String skuCode);
    boolean existsBySkuCode(String skuCode);
}
