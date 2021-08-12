package com.example.demo.inventory.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InventoryDTO implements Serializable {
    private Long id;
    private String skuCode;
    private Long quantity;
    private LocalDateTime lastUpdatedDate;
    private LocalDateTime createdDate;
}
