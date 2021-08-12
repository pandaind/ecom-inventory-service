package com.example.demo.inventory.web.rest;

import com.example.demo.inventory.service.InventoryService;
import com.example.demo.inventory.service.dto.InventoryDTO;
import com.example.demo.inventory.web.rest.errors.BadRequestAlertException;
import com.example.demo.inventory.web.rest.util.HeaderUtil;
import com.example.demo.inventory.web.rest.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class InventoryResource {

    private static final String ENTITY_NAME = "Inventory";

    @Value("${spring.application.name}")
    private String applicationName;

    private final InventoryService service;

    @Autowired
    public InventoryResource(InventoryService service) {
        this.service = service;
    }

    /**
     * {@code GET /inventories}: get all the inventories
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventories in body.
     */
    @GetMapping("/inventories")
    public ResponseEntity<List<InventoryDTO>> getInventories() {
        log.debug("REST request to get all inventories");
        var inventories = this.service.findAll();
        return ResponseEntity.ok().body(inventories);
    }

    @PostMapping("/inventories")
    public ResponseEntity<InventoryDTO> addInventory(@RequestBody InventoryDTO inventoryDTO) throws URISyntaxException {
        log.debug("REST request to save Inventory {}", inventoryDTO);
        if (inventoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }

        InventoryDTO result = this.service.add(inventoryDTO);

        return ResponseEntity.created(new URI("/inventories" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/inventories/{skuCode}")
    public ResponseEntity<InventoryDTO> updateInventory(
            @PathVariable(value = "skuCode", required = false) final String skuCode,
            @RequestBody InventoryDTO inventoryDTO) throws URISyntaxException {
        log.debug("REST request to update Inventory {}", inventoryDTO);

        validateUpdateRequest(skuCode, inventoryDTO);

        InventoryDTO result = this.service.update(inventoryDTO);

        return ResponseEntity.created(new URI("/inventories" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName,
                        false, ENTITY_NAME, result.getSkuCode()))
                .body(result);
    }

    @PatchMapping(value = "/inventories/{skuCode}", consumes = "application/merge-patch+json")
    public ResponseEntity<InventoryDTO> partialUpdateInventory(
            @PathVariable(value = "skuCode", required = false) final String skuCode,
            @RequestBody InventoryDTO inventoryDTO) {
        log.debug("REST request to partial update Inventory partially : {}, {}", skuCode, inventoryDTO);

        validateUpdateRequest(skuCode, inventoryDTO);

        Optional<InventoryDTO> result = this.service.partialUpdate(inventoryDTO);

        return ResponseUtil.wrapNotFound(result, HeaderUtil.createEntityCreationAlert(applicationName,
                false, ENTITY_NAME, skuCode));
    }

    @DeleteMapping("/inventories/{skuCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String skuCode) {
        log.debug("REST request to delete Product : {}", skuCode);
        this.service.delete(skuCode);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, skuCode))
                .build();
    }

    private void validateUpdateRequest(@PathVariable(value = "skuCode", required = false) String skuCode, @RequestBody InventoryDTO inventoryDTO) {
        if (inventoryDTO.getId() == null && inventoryDTO.getSkuCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(skuCode, inventoryDTO.getSkuCode())
                && !Objects.equals(skuCode, inventoryDTO.getSkuCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!this.service.existsBySkuCode(skuCode)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
    }

}
