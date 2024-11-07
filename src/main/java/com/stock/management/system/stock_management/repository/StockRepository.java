package com.stock.management.system.stock_management.repository;

import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface StockRepository extends JpaRepository<Stock,String> {

    boolean existsByItemName(String itemName);

    @Query("SELECT NEW com.stock.management.system.stock_management.api.dto.StockDto(s.serialNumber,s.itemName,s.price,s.salesPrice,s.amount,s.store,s.dateOfPurchase,s.category,s.status) " +
            "FROM Stock s " +
            "WHERE (:searchStock IS NULL OR s.itemName LIKE %:searchStock%) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR DATE(s.dateOfPurchase) BETWEEN :startDate AND :endDate)")
    Page<StockDto> getAllStock(Pageable pageable, String searchStock, LocalDate startDate, LocalDate endDate);

    @Query("SELECT NEW com.stock.management.system.stock_management.api.dto.StockDto(s.id,s.itemName,s.salesPrice,s.amount) " +
            "FROM Stock s " +
            "WHERE :itemName IS NULL OR s.itemName LIKE %:itemName% ")
    Page<StockDto> getAllStockForPos(String itemName, Pageable pageable);

    @Query("SELECT s FROM Stock s WHERE s.itemName = :itemName ")
    Stock findByItemName(String itemName);

}
