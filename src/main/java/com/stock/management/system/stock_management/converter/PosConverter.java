package com.stock.management.system.stock_management.converter;

import com.stock.management.system.stock_management.api.dto.PosDto;
import com.stock.management.system.stock_management.entity.Pos;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class PosConverter {

    public Pos convert(PosDto posDto){
        Pos pos = new Pos();
        pos.setId(posDto.getId());
        pos.setItemName(posDto.getItemName());
        pos.setQuantity(posDto.getQuantity());
        pos.setUnitPrice(posDto.getUnitPrice());
        Double amount = posDto.getUnitPrice() * posDto.getQuantity();
        pos.setAmount(amount);
        pos.setDate(LocalDate.now());
        pos.setTime(LocalTime.now());
        return pos;
    }
}
