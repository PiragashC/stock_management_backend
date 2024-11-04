package com.stock.management.system.stock_management.service;

import com.stock.management.system.stock_management.api.dto.PaginatedResponseDto;
import com.stock.management.system.stock_management.api.dto.ResponseDto;
import com.stock.management.system.stock_management.api.dto.StockDto;
import com.stock.management.system.stock_management.converter.StockConverter;
import com.stock.management.system.stock_management.entity.Stock;
import com.stock.management.system.stock_management.exception.ServiceException;
import com.stock.management.system.stock_management.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final StockConverter stockConverter;

    public ResponseDto addStock(StockDto stockDto){
        Stock stock = stockConverter.convert(stockDto);

        boolean isExists = stockRepository.existsByItemName(stock.getItemName());
        if (isExists){
            throw new ServiceException("Item name already exists","Bad request", HttpStatus.BAD_REQUEST);
        }
        stockRepository.save(stock);

        return new ResponseDto("Stock saved");
    }

    public StockDto getStockById(String stockId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new ServiceException("Stock not found","Bad request",HttpStatus.BAD_REQUEST));
        StockDto stockDto = stockConverter.convert(stock);
        return stockDto;
    }

    public ResponseDto deleteStock(String stockId) {
        stockRepository.findById(stockId).orElseThrow(() -> new ServiceException("Stock not found","Bad request",HttpStatus.BAD_REQUEST));
        stockRepository.deleteById(stockId);
        return new ResponseDto("Stock deleted");
    }

    public ResponseDto updateStock(StockDto stockDto) {
        Stock existingStock = stockRepository.findById(stockDto.getSerialNumber()).orElseThrow(() -> new ServiceException("Stock not found","Bad request",HttpStatus.BAD_REQUEST));
        stockRepository.save(stockConverter.convert(existingStock,stockDto));

        return new ResponseDto("Stock updated");
    }

    public PaginatedResponseDto<StockDto> getAllStock(int page, int size, String searchStock, LocalDate startDate,LocalDate endDate) {
        Pageable pageable = PageRequest.of(page - 1,size);
        Page<StockDto> stockDtos = stockRepository.getAllStock(pageable,searchStock,startDate,endDate);

        List<StockDto> stockDtoList = stockDtos.getContent();
        PaginatedResponseDto<StockDto> responseDto = new PaginatedResponseDto<>();
        responseDto.setData(stockDtoList);
        responseDto.setTotalPages(stockDtos.getTotalPages());
        responseDto.setTotalItems(stockDtos.getTotalElements());
        responseDto.setCurrentPage(page);

        return responseDto;

    }

    public ResponseDto updateStatus(String stockId, Boolean status) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new ServiceException("Stock not found","Bad request",HttpStatus.BAD_REQUEST));

        if (status.equals(stock.getStatus())){
            throw new ServiceException("Status is already " + status , "Bad request", HttpStatus.BAD_REQUEST);
        }
        stock.setStatus(status);
        stockRepository.save(stock);
        return new ResponseDto("Stock status updated");
    }
}
