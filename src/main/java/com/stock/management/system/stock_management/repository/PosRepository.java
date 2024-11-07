package com.stock.management.system.stock_management.repository;

import com.stock.management.system.stock_management.api.dto.PosDto;
import com.stock.management.system.stock_management.entity.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PosRepository extends JpaRepository<Pos,String> {

    @Query("SELECT NEW com.stock.management.system.stock_management.api.dto.PosDto(p.id, p.date, p.amount) " +
            "FROM Pos p " +
            "WHERE (:fromDate IS NULL OR :toDate IS NULL OR p.date BETWEEN :fromDate AND :toDate)")
    Page<PosDto> getAllTransactions(LocalDate fromDate, LocalDate toDate, Pageable pageable);

}
