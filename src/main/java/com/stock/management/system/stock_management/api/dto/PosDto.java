package com.stock.management.system.stock_management.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosDto {
    private String id;
    private String itemName;
    private Double unitPrice;
    private Integer quantity;
    private Double amount;
    private LocalDate date;
    private LocalTime time;
    private String billNo;

    public PosDto(String id, LocalDate date, Double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

}
