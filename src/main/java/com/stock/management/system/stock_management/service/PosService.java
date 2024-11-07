package com.stock.management.system.stock_management.service;

import com.stock.management.system.stock_management.api.dto.PaginatedResponseDto;
import com.stock.management.system.stock_management.api.dto.PosDto;
import com.stock.management.system.stock_management.api.dto.ResponseDto;
import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.converter.PosConverter;
import com.stock.management.system.stock_management.entity.Pos;
import com.stock.management.system.stock_management.entity.Stock;
import com.stock.management.system.stock_management.repository.PosRepository;
import com.stock.management.system.stock_management.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PosService {

    private final PosConverter posConverter;

    private final PosRepository posRepository;

    private final StockRepository stockRepository;

    public ResponseDto savePos(PosDto posDto){
        Pos savedPos = posRepository.save(posConverter.convert(posDto));

        Stock stock = stockRepository.findByItemName(savedPos.getItemName());
        Integer remainingStock = stock.getAmount() - savedPos.getQuantity();
        stock.setAmount(remainingStock);
        stockRepository.save(stock);
        return new ResponseDto("Pos saved");
    }

    public PaginatedResponseDto<StockDto> getAllStockForPos(String itemName,Integer page,Integer size) {
        Pageable pageable = PageRequest.of(page - 1,size);
        Page<StockDto> stockDtos = stockRepository.getAllStockForPos(itemName,pageable);
        List<StockDto> stockDtoList = stockDtos.getContent();

        PaginatedResponseDto<StockDto> responseDto = new PaginatedResponseDto<>();
        responseDto.setData(stockDtoList);
        responseDto.setCurrentPage(page);
        responseDto.setTotalItems(stockDtos.getTotalElements());
        responseDto.setTotalPages(stockDtos.getTotalPages());

        return responseDto;
    }

    public PaginatedResponseDto<PosDto> getAllTransactions(LocalDate fromDate, LocalDate toDate, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1,size);
        Page<PosDto> posDtos = posRepository.getAllTransactions(fromDate,toDate,pageable);
        List<PosDto> posDtosContent = posDtos.getContent();


        AtomicInteger counter = new AtomicInteger(1 + (page - 1) * size);
        posDtos.forEach(posDto ->
                posDto.setBillNo(String.format("BILL-%03d", counter.getAndIncrement()))
        );

        PaginatedResponseDto<PosDto> responseDto = new PaginatedResponseDto<>();
        responseDto.setData(posDtosContent);
        responseDto.setCurrentPage(page);
        responseDto.setTotalItems(posDtos.getTotalElements());
        responseDto.setTotalPages(posDtos.getTotalPages());

        return responseDto;
    }
}
