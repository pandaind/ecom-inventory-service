package com.example.demo.inventory.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sku_code", nullable = false, unique = true)
    private String skuCode;

    @Column(name = "quantity")
    private Long quantity;

    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
