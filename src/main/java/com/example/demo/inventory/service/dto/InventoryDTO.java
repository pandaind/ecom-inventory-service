package com.example.demo.inventory.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
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
