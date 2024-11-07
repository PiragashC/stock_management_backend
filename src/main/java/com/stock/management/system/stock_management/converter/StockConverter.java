package com.stock.management.system.stock_management.converter;

import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.entity.Stock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockConverter {

    public Stock convert(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setSerialNumber(stockDto.getSerialNumber());
        stock.setItemName(stockDto.getItemName());
        stock.setPrice(stockDto.getPrice());
        stock.setSalesPrice(stockDto.getSalesPrice());
        stock.setAmount(stockDto.getBackupQuantity());
        stock.setStore(stockDto.getStore());
        stock.setDateOfPurchase(LocalDateTime.now());
        stock.setCategory(stockDto.getCategory());
        stock.setStatus(Boolean.TRUE);
        return stock;
    }

    public StockDto convert(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setSerialNumber(stock.getSerialNumber());
        stockDto.setItemName(stock.getItemName());
        stockDto.setPrice(stock.getPrice());
        stockDto.setSalesPrice(stock.getSalesPrice());
        stockDto.setBackupQuantity(stock.getAmount());
        stockDto.setStore(stock.getStore());
        stockDto.setDateOfPurchase(stock.getDateOfPurchase());
        stockDto.setCategory(stock.getCategory());
        stockDto.setStatus(stock.getStatus());
        return stockDto;
    }

    public Stock convert(Stock existingStock,StockDto newStock){
        existingStock.setItemName(newStock.getItemName());
        existingStock.setPrice(newStock.getPrice());
        existingStock.setSalesPrice(newStock.getSalesPrice());
        existingStock.setAmount(newStock.getBackupQuantity());
        existingStock.setStore(newStock.getStore());
        existingStock.setCategory(newStock.getCategory());

        return existingStock;
    }
}
