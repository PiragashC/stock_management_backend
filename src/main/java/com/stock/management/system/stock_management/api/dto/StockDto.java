package com.stock.management.system.stock_management.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class StockDto {
    private String serialNumber;
    private String itemName;
    private Double price;
    private Double salesPrice;
    private Integer amount;
    private String store;
    private LocalDateTime dateOfPurchase;
    private String category;
    private Boolean status;

    public StockDto(String serialNumber, String itemName, Double price, Double salesPrice, Integer amount, String store, LocalDateTime dateOfPurchase, String category, Boolean status) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.price = price;
        this.salesPrice = salesPrice;
        this.amount = amount;
        this.store = store;
        this.dateOfPurchase = dateOfPurchase;
        this.category = category;
        this.status = status;
    }
}
