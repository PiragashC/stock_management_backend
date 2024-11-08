package com.stock.management.system.stock_management.api.controller;

import com.stock.management.system.stock_management.api.dto.PaginatedResponseDto;
import com.stock.management.system.stock_management.api.dto.ResponseDto;
import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseDto addStock(@RequestBody StockDto stockDto){
        return stockService.addStock(stockDto);
    }

    @GetMapping
    public StockDto getStockById(@RequestParam(value = "stockId") String stockId){
        return stockService.getStockById(stockId);
    }

    @DeleteMapping
    public ResponseDto deleteStock(@RequestParam(value = "stockId") String stockId){
        return stockService.deleteStock(stockId);
    }

    @PatchMapping
    public ResponseDto updateStock(@RequestBody StockDto stockDto){
        return stockService.updateStock(stockDto);
    }

    @GetMapping("/get-all-stock")
    public PaginatedResponseDto<StockDto> getAllStock(@RequestParam(value = "page") int page,
                                                      @RequestParam(value = "size") int size,
                                                      @RequestParam(value = "searchStock",required = false) String searchStock,
                                                      @RequestParam(value = "startDate",required = false) LocalDate startDate,
                                                      @RequestParam(value = "endDate",required = false) LocalDate endDate){
        return stockService.getAllStock(page,size,searchStock,startDate,endDate);
    }

    @PutMapping("/update-status")
    public ResponseDto updateStatus(@RequestParam(value = "stockId") String stockId,
                                    @RequestParam(value = "status") Boolean status){
        return stockService.updateStatus(stockId,status);
    }
}
