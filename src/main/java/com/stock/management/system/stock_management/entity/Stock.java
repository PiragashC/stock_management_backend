package com.stock.management.system.stock_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
public class Stock {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String serialNumber;
    private String itemName;
    private Double price;
    private Double salesPrice;
    private Integer amount;
    private String store;
    private LocalDateTime dateOfPurchase;
    private String category;
    private Boolean status;
}
