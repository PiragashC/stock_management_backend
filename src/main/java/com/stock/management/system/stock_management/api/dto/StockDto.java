package com.stock.management.system.stock_management.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto {
    private String serialNumber;
    private String itemName;
    private Double price;
    private Double salesPrice;
    private Integer backupQuantity;
    private String store;
    private LocalDateTime dateOfPurchase;
    private String category;
    private Boolean status;

    public StockDto(String serialNumber, String itemName, Double price, Double salesPrice, Integer amount, String store, LocalDateTime dateOfPurchase, String category, Boolean status) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.price = price;
        this.salesPrice = salesPrice;
        this.backupQuantity = amount;
        this.store = store;
        this.dateOfPurchase = dateOfPurchase;
        this.category = category;
        this.status = status;
    }

    public StockDto(String id,String itemName, Double salesPrice, Integer amount) {
        this.serialNumber = id;
        this.itemName = itemName;
        this.salesPrice = salesPrice;
        this.backupQuantity = amount;
    }
}
