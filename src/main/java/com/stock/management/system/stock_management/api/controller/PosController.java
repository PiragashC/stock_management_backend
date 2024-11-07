package com.stock.management.system.stock_management.api.controller;

import com.stock.management.system.stock_management.api.dto.PaginatedResponseDto;
import com.stock.management.system.stock_management.api.dto.PosDto;
import com.stock.management.system.stock_management.api.dto.ResponseDto;
import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.service.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/pos")
@RequiredArgsConstructor
public class PosController {

    private final PosService posService;

    @PostMapping
    public ResponseDto savePos(@RequestBody PosDto posDto){
        return posService.savePos(posDto);
    }

    @GetMapping
    public PaginatedResponseDto<StockDto> getAllStockForPos(@RequestParam(value = "itemName",required = false) String itemName,
                                                            @RequestParam(value = "page",required = false) Integer page,
                                                            @RequestParam(value = "size",required = false) Integer size){
        return posService.getAllStockForPos(itemName,page,size);
    }

    @GetMapping("/getAllPos")
    public PaginatedResponseDto<PosDto> getAllTransactions(@RequestParam(value = "fromDate",required = false) LocalDate fromDate,
                                                           @RequestParam(value = "toDate",required = false) LocalDate toDate,
                                                            @RequestParam(value = "page",required = false) Integer page,
                                                            @RequestParam(value = "size",required = false) Integer size){
        return posService.getAllTransactions(fromDate,toDate,page,size);
    }
}
